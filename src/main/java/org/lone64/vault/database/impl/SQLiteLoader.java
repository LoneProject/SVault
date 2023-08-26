package org.lone64.vault.database.impl;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.lone64.vault.database.Database;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLiteLoader implements Database {

    private String url;
    private Connection connection;

    public SQLiteLoader(JavaPlugin plugin) {
        File folder = plugin.getDataFolder();
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                plugin.getLogger().severe("Could not create folder!");
            }
        }

        folder = new File(plugin.getDataFolder() + "/cache");
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                plugin.getLogger().severe("Could not create folder!");
            }
        }
        File dataFolder = new File(folder.getPath() + "/cache.db");
        if (!dataFolder.exists()) {
            try {
                if (!dataFolder.createNewFile()) {
                    plugin.getLogger().severe("Could not create cache/cache.db file!");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        this.url = "jdbc:sqlite:" + dataFolder;
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.getConnection(this.url);
        } catch (SQLException | ClassNotFoundException e) {
            if (e instanceof ClassNotFoundException) {
                plugin.getLogger().severe("Could Not Found SQLite Driver on your system!");
            } else e.printStackTrace();
        }
    }

    @Override
    public void init() {
        String sql = "CREATE TABLE IF NOT EXISTS global_stats (id INTEGER PRIMARY KEY AUTOINCREMENT, uuid VARCHAR(36), amount DOUBLE);";
        try {
            checkConnection();
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setStats(UUID uuid, double amount) {
        String sql;
        try {
            checkConnection();
            if (hasStats(uuid)) {
                sql = "UPDATE global_stats SET amount = ? WHERE uuid = ?;";
                try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
                    statement.setDouble(1, amount);
                    statement.setString(2, uuid.toString());
                    statement.executeUpdate();
                }
            } else {
                sql = "INSERT INTO global_stats (uuid, amount) VALUES(?, ?);";
                try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
                    statement.setString(1, uuid.toString());
                    statement.setDouble(2, amount);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setStats(OfflinePlayer player, double amount) {
        setStats(player.getUniqueId(), amount);
    }

    @Override
    public boolean hasStats(UUID uuid) {
        String sql = "SELECT uuid FROM global_stats WHERE uuid = ?;";
        try {
            checkConnection();
            try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
                statement.setString(1, uuid.toString());
                try (ResultSet result = statement.executeQuery()) {
                    return result.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public double getAmount(UUID uuid) {
        String sql = "SELECT amount FROM global_stats WHERE uuid = ?;";
        try {
            checkConnection();
            try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
                statement.setString(1, uuid.toString());
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        return result.getDouble("amount");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
        return 0.0;
    }

    @Override
    public List<UUID> getPlayers() {
        List<UUID> playerList = new ArrayList<>();
        String sql = "SELECT uuid FROM global_stats";
        try {
            checkConnection();
            try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        playerList.add(UUID.fromString(result.getString("uuid")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return playerList;
        }
        return playerList;
    }

    private void checkConnection() throws SQLException {
        boolean renew = false;

        if (this.connection == null)
            renew = true;
        else
        if (this.connection.isClosed())
            renew = true;

        if (renew)
            this.connection = DriverManager.getConnection(this.url);
    }

}
