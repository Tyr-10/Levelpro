package com.levelpro.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "academic_levels")
public class AcademicLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre que la institución quiera darle:
     * Facultad, Programa, Grupo, Curso, Semestre, etc.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Nivel jerárquico numérico:
     * 1 = institución
     * 2 = siguiente nivel
     * 3 = siguiente…
     */
    @Column(nullable = false)
    private int levelOrder;

    /**
     * Relación con la institución
     * IMPRESCINDIBLE para evitar cruces
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    /**
     * Nivel padre (self reference)
     * Permite jerarquía infinita
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private AcademicLevel parent;

    /**
     * Hijos del nivel actual
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AcademicLevel> children = new HashSet<>();

    /**
     * Permite desactivar niveles sin borrar
     */
    @Column(nullable = false)
    private boolean active = true;

    /* =========================
       GETTERS & SETTERS
       ========================= */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelOrder() {
        return levelOrder;
    }

    public void setLevelOrder(int levelOrder) {
        this.levelOrder = levelOrder;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public AcademicLevel getParent() {
        return parent;
    }

    public void setParent(AcademicLevel parent) {
        this.parent = parent;
    }

    public Set<AcademicLevel> getChildren() {
        return children;
    }

    public void setChildren(Set<AcademicLevel> children) {
        this.children = children;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
