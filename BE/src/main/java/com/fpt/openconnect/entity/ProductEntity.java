    package com.fpt.openconnect.entity;

    import com.fpt.openconnect.entity.keys.ProductTicketOrderKeys;
    import jakarta.persistence.*;

    import java.util.Date;
    import java.util.List;

    @Entity(name = "product")
    public class ProductEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "name")
        private String name;

        @Column(name = "description")
        private String description;

        @Column(name = "quantity")
        private int quantity;

        @Column(name = "price")
        private double price;

        @ManyToOne
        @JoinColumn(name = "id_category_product")
        private CategoryProductEntity categoryProduct;

        @OneToMany(mappedBy = "productEntity")
        private List<ImageProductEntity> imageProductEntities;

        @ManyToOne
        @JoinColumn(name = "id_company_product")
        private CompanyEntity companyProduct;

        @OneToMany(mappedBy = "productEntity")
        private List<ProductTicketOrderEntity> productTicketOrderEntities;

        @Column(name = "create_date")
        private Date createDate;

        @OneToMany(mappedBy = "productEntity")
        private List<CartEntity> cartEntities;


        public Date getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public CategoryProductEntity getCategoryProduct() {
            return categoryProduct;
        }

        public void setCategoryProduct(CategoryProductEntity categoryProduct) {
            this.categoryProduct = categoryProduct;
        }

        public List<ImageProductEntity> getImageProductEntities() {
            return imageProductEntities;
        }

        public void setImageProductEntities(List<ImageProductEntity> imageProductEntities) {
            this.imageProductEntities = imageProductEntities;
        }

        public CompanyEntity getCompanyProduct() {
            return companyProduct;
        }

        public void setCompanyProduct(CompanyEntity companyProduct) {
            this.companyProduct = companyProduct;
        }

        public List<ProductTicketOrderEntity> getProductTicketOrderEntities() {
            return productTicketOrderEntities;
        }

        public void setProductTicketOrderEntities(List<ProductTicketOrderEntity> productTicketOrderEntities) {
            this.productTicketOrderEntities = productTicketOrderEntities;
        }

        public List<CartEntity> getCartEntities() {
            return cartEntities;
        }

        public void setCartEntities(List<CartEntity> cartEntities) {
            this.cartEntities = cartEntities;
        }


    }
