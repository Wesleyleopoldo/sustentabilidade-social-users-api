package com.syntech.sustentabilidadesocial.sustentabilidade_social.utils;

import java.text.Normalizer;
import java.util.Hashtable;
import java.util.Locale;
import java.util.UUID;

import javax.naming.directory.*;

public class UserUtils {
    public static boolean isDomainValid(String email) {
        String domain = email.substring(email.indexOf("@") + 1);

        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext ctx = new InitialDirContext(env);
            Attributes attrs = ctx.getAttributes(domain, new String[] { "MX" });
            Attribute attr = attrs.get("MX");
            return attr != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static String generateSlug(String name) {
        
        String slug = Normalizer.normalize(name, Normalizer.Form.NFD)
        .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
        .toLowerCase(Locale.ROOT)
        .replaceAll("[^a-z0-9\\s-]", "")
        .replaceAll("\\s+", "-")
        .replaceAll("-{2,}", "-")
        .replaceAll("^-|-$", "");

        String hash = UUID.randomUUID().toString().substring(0, 14);
        return slug + "-" + hash;
    }
}
