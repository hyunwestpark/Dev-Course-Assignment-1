package org.example.database;

import java.util.List;

public interface Database<T> {
    void create();
    T read(int id);
    void update(int id);
    void delete(int id);
    void traverse();
}
