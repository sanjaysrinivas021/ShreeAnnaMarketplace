package com.shreeanna.dao;

import com.shreeanna.model.Product;
import com.shreeanna.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public boolean addProduct(Product p) {
    	String sql = "INSERT INTO products(name, description, category, " +
                "price_per_kg, quantity_kg, farmer_id, state, certification, image) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getCategory());
            ps.setDouble(4, p.getPricePerKg());
            ps.setInt(5, p.getQuantityKg());
            ps.setInt(6, p.getFarmerId());
            ps.setString(7, p.getState());
            ps.setString(8, p.getCertification());
            ps.setString(9, p.getImage());


            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, u.name as farmer_name FROM products p " +
                     "LEFT JOIN users u ON p.farmer_id = u.id " +
                     "ORDER BY p.id DESC";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setCategory(rs.getString("category"));
                p.setPricePerKg(rs.getDouble("price_per_kg"));
                p.setQuantityKg(rs.getInt("quantity_kg"));
                p.setFarmerId(rs.getInt("farmer_id"));
                p.setState(rs.getString("state"));
                p.setCertification(rs.getString("certification"));
                p.setImage(rs.getString("image"));

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setCategory(rs.getString("category"));
                p.setPricePerKg(rs.getDouble("price_per_kg"));
                p.setQuantityKg(rs.getInt("quantity_kg"));
                p.setFarmerId(rs.getInt("farmer_id"));
                p.setState(rs.getString("state"));
                p.setCertification(rs.getString("certification"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getProductsByFarmer(int farmerId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE farmer_id=? ORDER BY id DESC";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, farmerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setCategory(rs.getString("category"));
                p.setPricePerKg(rs.getDouble("price_per_kg"));
                p.setQuantityKg(rs.getInt("quantity_kg"));
                p.setFarmerId(rs.getInt("farmer_id"));
                p.setState(rs.getString("state"));
                p.setCertification(rs.getString("certification"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
