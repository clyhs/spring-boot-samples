package org.abigfish.neo4j.service;

import java.util.List;

import org.abigfish.neo4j.dao.SupplierRepository;
import org.abigfish.neo4j.entity.Product;
import org.abigfish.neo4j.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional  // Neo4j默认启用事务
    public Supplier createSupplier(String name, List<Product> products) {
        Supplier supplier = new Supplier();
        supplier.setName(name);
        supplier.setProducts(products);
        return supplierRepository.save(supplier);
    }
    
    public List<Supplier> getSuppliersByProduct(String productName) {
        return supplierRepository.findSuppliersByProductName(productName);
    }
}