package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.model.Tag;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TagsRepository implements PanacheRepository<Tag> {
    public Tag findByLabel(String label){
        return find("label", label).firstResult();
    }
}
