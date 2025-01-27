package com.example.demo.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "surface")
    @JsonProperty("surface")
    private Double surface;

    @Column(name = "price")
    @JsonProperty("price")
    private Double price;

    @Column(name = "picture")
    private String picture;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "owner_id")
    @JsonProperty("owner_id")
    private Long ownerId;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("updated_at")
    private Date updatedAt;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "rental_id")
    @JsonIgnore
    private List<RentalPicture> pictures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<RentalPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<RentalPicture> pictures) {
        this.pictures = pictures;
    }

    @JsonProperty("picture")
    public String getPictureUrl() {
        if (pictures != null && !pictures.isEmpty()) {
            return pictures.get(0).getPicture();
        }
        return picture != null ? picture : "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg";
    }

    @JsonProperty("name")
    public String getRentalName() {
        return name;
    }
}