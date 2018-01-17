/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartlab.dao;

import br.com.smartlab.model.SensorData;
import br.com.smartlab.model.SensorType;
import br.com.smartlab.model.Unit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author george
 */
public class SensorDataDAO {

    private Connection conn;

    public SensorDataDAO() {

        this.conn = new ConnectionFactory().getConnection();
    }

    public SensorData getActualSensorValue(String id) {
        SensorData sensorData = new SensorData();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM topiv.sensor_data where idsensor = '" + id + "' order by time desc limit 1");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sensorData.setId(rs.getString("id"));
                sensorData.setDate(rs.getString("time"));
                sensorData.setIdSensor(rs.getString("idSensor"));
                sensorData.setSensorType(SensorType.valueOf(rs.getString("sensor_type")));
                sensorData.setUnit(Unit.valueOf(rs.getString("unit")));
                sensorData.setValue(rs.getString("value"));
            }
            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(SensorDataDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(SensorDataDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return sensorData;
    }

    public ArrayList<SensorData> getAllSensorValues(String id) {
        ArrayList<SensorData> data = new ArrayList<>();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM topiv.sensor_data where idsensor = '" + id + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SensorData sensorData = new SensorData();
                sensorData.setId(rs.getString("id"));
                sensorData.setDate(rs.getString("time"));
                sensorData.setIdSensor(rs.getString("idSensor"));
                sensorData.setSensorType(SensorType.valueOf(rs.getString("sensor_type")));
                sensorData.setUnit(Unit.valueOf(rs.getString("unit")));
                sensorData.setValue(rs.getString("value"));
                data.add(sensorData);
            }
            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(SensorDataDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SensorDataDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return data;
    }

    public ArrayList<SensorData> getSensorValuesByDate(String id, String startTime, String endTime) {
        ArrayList<SensorData> data = new ArrayList<>();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM topiv.sensor_data where idsensor = '" + id + "' and (time >= '" + startTime + "' and time <= '" + endTime + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SensorData sensorData = new SensorData();
                sensorData.setId(rs.getString("id"));
                sensorData.setDate(rs.getString("time"));
                sensorData.setIdSensor(rs.getString("idSensor"));
                sensorData.setSensorType(SensorType.valueOf(rs.getString("sensor_type")));
                sensorData.setUnit(Unit.valueOf(rs.getString("unit")));
                sensorData.setValue(rs.getString("value"));
                data.add(sensorData);
            }
            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(SensorDataDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SensorDataDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return data;
    }

}
