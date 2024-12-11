package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private final DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle)
    {
        String sql = "INSERT INTO vehicles (VIN, make, model, year, SOLD, color, vehicleType, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, vehicle.getVin());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setBoolean(5, vehicle.isSold());
            stmt.setString(6, vehicle.getColor());
            stmt.setString(7, vehicle.getVehicleType());
            stmt.setInt(8, vehicle.getOdometer());
            stmt.setDouble(9, vehicle.getPrice());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void removeVehicle(String VIN)
    {
        String sql = "DELETE FROM vehicles WHERE VIN = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, VIN);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice)
    {
        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            try (ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next())
                {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model)
    {
        String sql = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, make);
            stmt.setString(2, model);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next())
                {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear)
    {
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, minYear);
            stmt.setInt(2, maxYear);
            try (ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next())
                {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color)
    {
        String sql = "SELECT * FROM vehicles WHERE color = ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, color);
            try (ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next())
                {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage)
    {
        String sql = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, minMileage);
            stmt.setInt(2, maxMileage);
            try (ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next())
                {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByType(String type)
    {
        String sql = "SELECT * FROM vehicles WHERE vehicleType = ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, type);
            try (ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next())
                {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}