package com.trackmyfix.trackmyfix.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class DataLoader {
//
//    @Bean
//    public CommandLineRunner loadData(RoleRepository roleRepository, UserRepository userRepository, OrderRepository orderRepository,
//                                      ActionRepository actionRepository, MovementRepository movementRepository, StateRepository stateRepository,
//                                      TypeRepository typeRepository, DeviceRepository deviceRepository, ActionUserRepository actionUserRepository,
//                                      UserChangesRepository userChangesRepository) {
//        return args -> {
//            // Crear roles
//            Role adminRole = new Role(null, "ADMIN", null);
//            Role employeeRole = new Role(null, "EMPLOYEE", null);
//            Role clientRole = new Role(null, "CLIENT", null);
//
//            roleRepository.saveAll(Arrays.asList(adminRole, employeeRole, clientRole));
//
//            // Crear usuarios
//            Admin admin1 = new Admin(null, "Admin1", "One", "admin1@example.com", "1234567891", "Address 1", "123451", "password", true, adminRole, new Date(), new Date());
//            Admin admin2 = new Admin(null, "Admin2", "Two", "admin2@example.com", "1234567892", "Address 2", "123452", "password", true, adminRole, new Date(), new Date());
//            Admin admin3 = new Admin(null, "Admin3", "Three", "admin3@example.com", "1234567893", "Address 3", "123453", "password", true, adminRole, new Date(), new Date());
//            Admin admin4 = new Admin(null, "Admin4", "Four", "admin4@example.com", "1234567894", "Address 4", "123454", "password", true, adminRole, new Date(), new Date());
//            Admin admin5 = new Admin(null, "Admin5", "Five", "admin5@example.com", "1234567895", "Address 5", "123455", "password", true, adminRole, new Date(), new Date());
//
//            Employee employee1 = new Employee(null, "Employee1", "One", "employee1@example.com", "9876543211", "Address 6", "543211", "password", true, employeeRole, new Date(), new Date());
//            Employee employee2 = new Employee(null, "Employee2", "Two", "employee2@example.com", "9876543212", "Address 7", "543212", "password", true, employeeRole, new Date(), new Date());
//            Employee employee3 = new Employee(null, "Employee3", "Three", "employee3@example.com", "9876543213", "Address 8", "543213", "password", true, employeeRole, new Date(), new Date());
//            Employee employee4 = new Employee(null, "Employee4", "Four", "employee4@example.com", "9876543214", "Address 9", "543214", "password", true, employeeRole, new Date(), new Date());
//            Employee employee5 = new Employee(null, "Employee5", "Five", "employee5@example.com", "9876543215", "Address 10", "543215", "password", true, employeeRole, new Date(), new Date());
//
//            Client client1 = new Client(null, "Client1", "One", "client1@example.com", "5551234561", "Address 11", "111111", "password", true, clientRole, new Date(), new Date());
//            Client client2 = new Client(null, "Client2", "Two", "client2@example.com", "5551234562", "Address 12", "111112", "password", true, clientRole, new Date(), new Date());
//            Client client3 = new Client(null, "Client3", "Three", "client3@example.com", "5551234563", "Address 13", "111113", "password", true, clientRole, new Date(), new Date());
//            Client client4 = new Client(null, "Client4", "Four", "client4@example.com", "5551234564", "Address 14", "111114", "password", true, clientRole, new Date(), new Date());
//            Client client5 = new Client(null, "Client5", "Five", "client5@example.com", "5551234565", "Address 15", "111115", "password", true, clientRole, new Date(), new Date());
//            Client client6 = new Client(null, "Client6", "Six", "client6@example.com", "5551234566", "Address 16", "111116", "password", true, clientRole, new Date(), new Date());
//            Client client7 = new Client(null, "Client7", "Seven", "client7@example.com", "5551234567", "Address 17", "111117", "password", true, clientRole, new Date(), new Date());
//            Client client8 = new Client(null, "Client8", "Eight", "client8@example.com", "5551234568", "Address 18", "111118", "password", true, clientRole, new Date(), new Date());
//            Client client9 = new Client(null, "Client9", "Nine", "client9@example.com", "5551234569", "Address 19", "111119", "password", true, clientRole, new Date(), new Date());
//            Client client10 = new Client(null, "Client10", "Ten", "client10@example.com", "5551234570", "Address 20", "111110", "password", true, clientRole, new Date(), new Date());
//
//            userRepository.saveAll(Arrays.asList(admin1, admin2, admin3, admin4, admin5, employee1, employee2, employee3, employee4, employee5, client1, client2, client3, client4, client5, client6, client7, client8, client9, client10));
//
//            // Crear orders
//            Order order1 = new Order(null, "ORD001", null, "Order 1", 100.0, 150.0, client1, new Date(), new Date());
//            Order order2 = new Order(null, "ORD002", null, "Order 2", 200.0, 250.0, client2, new Date(), new Date());
//            Order order3 = new Order(null, "ORD003", null, "Order 3", 300.0, 350.0, client3, new Date(), new Date());
//            Order order4 = new Order(null, "ORD004", null, "Order 4", 400.0, 450.0, client4, new Date(), new Date());
//            Order order5 = new Order(null, "ORD005", null, "Order 5", 500.0, 550.0, client5, new Date(), new Date());
//            Order order6 = new Order(null, "ORD006", null, "Order 6", 600.0, 650.0, client6, new Date(), new Date());
//            Order order7 = new Order(null, "ORD007", null, "Order 7", 700.0, 750.0, client7, new Date(), new Date());
//            Order order8 = new Order(null, "ORD008", null, "Order 8", 800.0, 850.0, client8, new Date(), new Date());
//            Order order9 = new Order(null, "ORD009", null, "Order 9", 900.0, 950.0, client9, new Date(), new Date());
//            Order order10 = new Order(null, "ORD010", null, "Order 10", 1000.0, 1050.0, client10, new Date(), new Date());
//
//            orderRepository.saveAll(Arrays.asList(order1, order2, order3, order4, order5, order6, order7, order8, order9, order10));
//
//            // Crear acciones
//            Action action1 = new Action(null, "LOGIN");
//            Action action2 = new Action(null, "LOGOUT");
//            Action action3 = new Action(null, "CREATE_ORDER");
//            Action action4 = new Action(null, "UPDATE_ORDER");
//            Action action5 = new Action(null, "DELETE_ORDER");
//
//            actionRepository.saveAll(Arrays.asList(action1, action2, action3, action4, action5));
//
//            // Crear movimientos
//            Movement movement1 = new Movement(null, admin1, order1, new Date(), action1, "Admin login");
//            Movement movement2 = new Movement(null, employee1, order2, new Date(), action2, "Employee logout");
//            Movement movement3 = new Movement(null, client1, order3, new Date(), action3, "Client created order");
//            Movement movement4 = new Movement(null, admin2, order4, new Date(), action4, "Admin updated order");
//            Movement movement5 = new Movement(null, employee2, order5, new Date(), action5, "Employee deleted order");
//            Movement movement6 = new Movement(null, client2, order6, new Date(), action1, "Client login");
//            Movement movement7 = new Movement(null, admin3, order7, new Date(), action2, "Admin logout");
//            Movement movement8 = new Movement(null, employee3, order8, new Date(), action3, "Employee created order");
//            Movement movement9 = new Movement(null, client3, order9, new Date(), action4, "Client updated order");
//            Movement movement10 = new Movement(null, admin4, order10, new Date(), action5, "Admin deleted order");
//
//            movementRepository.saveAll(Arrays.asList(movement1, movement2, movement3, movement4, movement5, movement6, movement7, movement8, movement9, movement10));
//
//            // Crear estados
//            State state1 = new State(null, "NEW");
//            State state2 = new State(null, "REPAIRED");
//            State state3 = new State(null, "DISPATCHED");
//            State state4 = new State(null, "DELIVERED");
//            State state5 = new State(null, "CANCELLED");
//
//            stateRepository.saveAll(Arrays.asList(state1, state2, state3, state4, state5));
//
//            // Crear tipos
//            Type type1 = new Type(null, "Laptop");
//            Type type2 = new Type(null, "Phone");
//            Type type3 = new Type(null, "Tablet");
//            Type type4 = new Type(null, "Desktop");
//            Type type5 = new Type(null, "Wearable");
//
//            typeRepository.saveAll(Arrays.asList(type1, type2, type3, type4, type5));
//
//            // Crear dispositivos
//            Device device1 = new Device(null, "Model A", "SN12345", "Charger", "New device", order1, type1, state1, new Date(), new Date());
//            Device device2 = new Device(null, "Model B", "SN54321", "Cover", "Used device", order2, type2, state2, new Date(), new Date());
//            Device device3 = new Device(null, "Model C", "SN67890", "Keyboard", "Refurbished device", order3, type3, state3, new Date(), new Date());
//            Device device4 = new Device(null, "Model D", "SN09876", "Mouse", "Old device", order4, type4, state4, new Date(), new Date());
//            Device device5 = new Device(null, "Model E", "SN11223", "Stand", "Damaged device", order5, type5, state5, new Date(), new Date());
//            Device device6 = new Device(null, "Model F", "SN44556", "Headphones", "New device", order6, type1, state1, new Date(), new Date());
//            Device device7 = new Device(null, "Model G", "SN77889", "Cable", "Used device", order7, type2, state2, new Date(), new Date());
//            Device device8 = new Device(null, "Model H", "SN99000", "Adapter", "Refurbished device", order8, type3, state3, new Date(), new Date());
//            Device device9 = new Device(null, "Model I", "SN11334", "Battery", "Old device", order9, type4, state4, new Date(), new Date());
//            Device device10 = new Device(null, "Model J", "SN55667", "Screen Protector", "Damaged device", order10, type5, state5, new Date(), new Date());
//
//            deviceRepository.saveAll(Arrays.asList(device1, device2, device3, device4, device5, device6, device7, device8, device9, device10));
//
//            // Crear acciones de usuario
//            ActionUser actionUser1 = new ActionUser(null, "CREATE");
//            ActionUser actionUser2 = new ActionUser(null, "READ");
//            ActionUser actionUser3 = new ActionUser(null, "UPDATE");
//            ActionUser actionUser4 = new ActionUser(null, "DELETE");
//            ActionUser actionUser5 = new ActionUser(null, "EXPORT");
//
//            actionUserRepository.saveAll(Arrays.asList(actionUser1, actionUser2, actionUser3, actionUser4, actionUser5));
//
//            // Crear cambios de usuario
//            UserChanges userChanges1 = new UserChanges(null, actionUser1, admin1, new Date());
//            UserChanges userChanges2 = new UserChanges(null, actionUser2, admin2, new Date());
//            UserChanges userChanges3 = new UserChanges(null, actionUser3, admin3, new Date());
//            UserChanges userChanges4 = new UserChanges(null, actionUser4, admin4, new Date());
//            UserChanges userChanges5 = new UserChanges(null, actionUser5, admin5, new Date());
//            UserChanges userChanges6 = new UserChanges(null, actionUser1, employee1, new Date());
//            UserChanges userChanges7 = new UserChanges(null, actionUser2, employee2, new Date());
//            UserChanges userChanges8 = new UserChanges(null, actionUser3, employee3, new Date());
//            UserChanges userChanges9 = new UserChanges(null, actionUser4, employee4, new Date());
//            UserChanges userChanges10 = new UserChanges(null, actionUser5, employee5, new Date());
//
//            userChangesRepository.saveAll(Arrays.asList(userChanges1, userChanges2, userChanges3, userChanges4, userChanges5, userChanges6, userChanges7, userChanges8, userChanges9, userChanges10));
//        };
//    }
}
