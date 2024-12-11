package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao
{
    private final DataSource dataSource;

    public LeaseDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract)
    {
        String sql = "INSERT INTO lease_contracts (contractId, vehicleVin, leaseStart, leaseEnd, monthlyPayment) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, leaseContract.getContractId());
            stmt.setString(2, leaseContract.getVin());
            stmt.setDate(3, java.sql.Date.valueOf(leaseContract.getLeaseStart()));
            stmt.setDate(4, java.sql.Date.valueOf(leaseContract.getLeaseEnd()));
            stmt.setDouble(5, leaseContract.getMonthlyPayment());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}