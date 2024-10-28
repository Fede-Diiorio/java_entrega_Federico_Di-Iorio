package com.coderhouse.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coderhouse.dao.DaoFactory;
import com.coderhouse.models.Product;
import com.coderhouse.models.ProductCategory;

@Component
public class DatabaseLoader {

	@Autowired
	private DaoFactory dao;
	
	private ProductCategory category1 = new ProductCategory("peluche");
	private ProductCategory category2 = new ProductCategory("colgante");
	private ProductCategory category3 = new ProductCategory("remera");
	private ProductCategory category4 = new ProductCategory("varita");
	
	public void loadCategories() {
		try {
			dao.createCategory(category1);
			dao.createCategory(category2);
			dao.createCategory(category3);
			dao.createCategory(category4);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public void loadProducts() {
	    try {
	        ProductCategory peluche = dao.getCategoryById(1);
	        ProductCategory colgante = dao.getCategoryById(2);
	        ProductCategory remera = dao.getCategoryById(3);
	        ProductCategory varita = dao.getCategoryById(4);

	        Product product1 = new Product(
	            "Peluche de Draco Malfoy",
	            "../pelucheDraco.webp",
	            "Experimenta la elegancia y el encanto con este peluche de Draco Malfoy...",
	            "abc123",
	            15,
	            10,
	            peluche
	        );
	        Product product2 = new Product(
	            "Colgante de Reliquias de la Muerte",
	            "../colganteReliquias.webp",
	            "Un colgante místico que representa las tres Reliquias de la Muerte...",
	            "def456",
	            28,
	            4,
	            colgante
	        );
	        Product product3 = new Product(
	            "Remera de Reliquias de la Muerte",
	            "../remeraReliquias.webp",
	            "Una remera intrigante con el símbolo de las Reliquias de la Muerte...",
	            "ghi789",
	            59,
	            11,
	            remera
	        );
	        Product product4 = new Product(
	            "Remera de Plataforma 9 3/4",
	            "../remera9-34.webp",
	            "Una elegante remera con el emblema de la Plataforma 9 3/4...",
	            "jkl012",
	            51,
	            11,
	            remera
	        );
	        Product product5 = new Product(
	            "Peluche de Harry Potter",
	            "../pelucheHarry.webp",
	            "Lleva contigo la magia de Hogwarts con este encantador peluche de Harry Potter...",
	            "mno345",
	            15,
	            10,
	            peluche
	        );
	        Product product6 = new Product(
	            "Colgante de Harry Potter",
	            "../colganteHarry.webp",
	            "Un encantador colgante inspirado en el famoso mago Harry Potter...",
	            "pqr678",
	            26,
	            4,
	            colgante
	        );
	        Product product7 = new Product(
	            "Remera de Emblema de Hogwarts",
	            "../remeraEmblema.webp",
	            "Luce con orgullo el emblema de Hogwarts con esta vibrante remera...",
	            "stu901",
	            52,
	            11,
	            remera
	        );
	        Product product8 = new Product(
	            "Peluche de Lord Voldemort",
	            "../pelucheVoldemort.webp",
	            "Un adorable peluche que captura la esencia misteriosa y tenebrosa de Lord Voldemort...",
	            "vwx234",
	            15,
	            10,
	            peluche
	        );
	        Product product9 = new Product(
	            "Colgante de Plataforma 9 3/4",
	            "../colgante9-34.webp",
	            "Un encantador colgante que representa la entrada a la Plataforma 9 3/4...",
	            "yza567",
	            30,
	            4,
	            colgante
	        );
	        Product product10 = new Product(
	            "Varita de Harry Potter",
	            "../varitaHarry.webp",
	            "La icónica varita de Harry Potter, conocida por sus hazañas...",
	            "bcd890",
	            15,
	            20,
	            varita
	        );
	        Product product11 = new Product(
	            "Varita de Sauco",
	            "../varitaSauco.webp",
	            "Una poderosa varita esculpida en madera de saúco...",
	            "efg123",
	            15,
	            20,
	            varita
	        );
	        Product product12 = new Product(
	            "Varita de Voldemort",
	            "../varitaVoldemort.webp",
	            "La oscura varita de Lord Voldemort...",
	            "hij456",
	            15,
	            20,
	            varita
	        );

	        dao.createProduct(product1);
	        dao.createProduct(product2);
	        dao.createProduct(product3);
	        dao.createProduct(product4);
	        dao.createProduct(product5);
	        dao.createProduct(product6);
	        dao.createProduct(product7);
	        dao.createProduct(product8);
	        dao.createProduct(product9);
	        dao.createProduct(product10);
	        dao.createProduct(product11);
	        dao.createProduct(product12);
	        
	        System.out.println("Productos cargados exitosamente.");
	        
	    } catch (Exception e) {
	        e.printStackTrace(System.err);
	    }
	}

}
