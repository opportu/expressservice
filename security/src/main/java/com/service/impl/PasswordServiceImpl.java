package com.service.impl;

import com.service.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashService;
import org.apache.shiro.crypto.hash.format.HashFormat;
import org.apache.shiro.crypto.hash.format.HashFormatFactory;
import org.apache.shiro.crypto.hash.format.ParsableHashFormat;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PasswordServiceImpl implements PasswordService {

    private HashFormat hashFormat;
    private volatile boolean hashFormatWarned = false;
    @Override
    public String encryptPassword(Object plaintext) {
        DefaultPasswordService defaultPasswordService = new DefaultPasswordService();
        Hash hash = defaultPasswordService.hashPassword(plaintext);
        this.checkHashFormatDurability();
        return this.hashFormat.format(hash);
    }


    protected void checkHashFormatDurability() {
        if (!this.hashFormatWarned) {
            HashFormat format = this.hashFormat;
            if (!(format instanceof ParsableHashFormat) && log.isWarnEnabled()) {
                String msg = "The configured hashFormat instance [" + format.getClass().getName() + "] is not a " + ParsableHashFormat.class.getName() + " implementation.  This is " + "required if you wish to support backwards compatibility for saved password checking (almost " + "always desirable).  Without a " + ParsableHashFormat.class.getSimpleName() + " instance, " + "any hashService configuration changes will break previously hashed/saved passwords.";
                log.warn(msg);
                this.hashFormatWarned = true;
            }
        }

    }
}
