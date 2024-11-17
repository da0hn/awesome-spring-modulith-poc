package dev.da0hn.library.management.system.catalog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.validator.constraints.ISBN;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
@Builder(toBuilder = true)
public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 7444007494924518949L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false, updatable = false)
    private String title;

    @ISBN
    @NotNull
    @Column(nullable = false, updatable = false, unique = true)
    private String isbn;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public void changeTitle(final String title) {
        this.title = title;
        this.updatedAt = Instant.now();
    }

    public void changeIsbn(final String isbn) {
        this.isbn = isbn;
        this.updatedAt = Instant.now();
    }

    public void changeCategory(final Category category) {
        this.category = category;
        this.updatedAt = Instant.now();
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
            ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : this.getClass().hashCode();
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null) return false;
        final Class<?> oEffectiveClass = o instanceof HibernateProxy ?
            ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        final Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
            ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        final Book book = (Book) o;
        return this.id != null && Objects.equals(this.id, book.id);
    }

}
