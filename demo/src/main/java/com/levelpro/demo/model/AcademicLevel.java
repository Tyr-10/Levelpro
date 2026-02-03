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
     * Nombre totalmente libre:
     * Campus, Facultad, Jornada, Materia, Cohorte, etc.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Orden jerárquico (1..N)
     */
    @Column(nullable = false)
    private int levelOrder;

    /**
     * Define si en este nivel se pueden asignar ESTUDIANTES
     */
    @Column(nullable = false)
    private boolean allowsStudents;

    /**
     * Define si en este nivel se pueden asignar DOCENTES
     */
    @Column(nullable = false)
    private boolean allowsTeachers;

    /**
     * Relación con la institución
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    /**
     * Jerarquía
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private AcademicLevel parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AcademicLevel> children = new HashSet<>();

    @Column(nullable = false)
    private boolean active = true;

    // getters y setters




    

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
