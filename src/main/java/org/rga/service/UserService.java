package org.rga.service;

import io.smallrye.mutiny.Uni;
import org.rga.entity.MasUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@ApplicationScoped
public class UserService {
    @Inject
    EntityManager em;

    public Uni<MasUser> findUserById(int id) {
        //return userRepository.findById(id).get();
        return Uni.createFrom().item(em.find(MasUser.class, id));
    }

    @Transactional
    public MasUser add(MasUser user) {
        if (user.getId() == null) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
       }
    }

    @Transactional
    public MasUser update(MasUser user) {
        MasUser userUpdate = em.find(MasUser.class,user.getId());
        userUpdate.setSend(true);
        return em.merge(userUpdate);
    }
}
