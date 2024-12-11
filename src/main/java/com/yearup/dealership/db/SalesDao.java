package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract)
    {
        String sql = "INSERT INTO sales_contracts (contractId, vehicleVin, saleDate, price) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, salesContract.getContractId());
            stmt.setString(2, salesContract.getVin());
            stmt.setDate(3, java.sql.Date.valueOf(salesContract.getSaleDate()));
            stmt.setDouble(4, salesContract.getPrice());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}