package com.trade.billing.service

import com.trade.billing.repository.UserRepository
import org.apache.catalina.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserSecurityService<UserDetailsService> : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository
    @Override
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val userEntity = userRepository.findByUsername(username)
            ?: throw
            UsernameNotFoundException(
                "User $username not found."
            )

        val roles: Array<String?> = userEntity.roles?.map {
                role -> role.role }!!.toTypedArray()

        return User.builder()
            .username(userEntity.username)
            .password(userEntity.password)
            .roles(*roles)
            .accountLocked(userEntity.locked!!)
            .disabled(userEntity.disabled!!)
            .build()
    }
}