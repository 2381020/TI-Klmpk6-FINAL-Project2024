module todoapp {
    requires java.sql; // Modul dasar Java
    exports todoapp.services;
    exports todoapp.views;
    exports todoapp.entities;
}
