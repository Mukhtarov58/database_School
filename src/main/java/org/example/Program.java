package org.example;

import org.example.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Program {
    public static void main(String[] args) {
        // Создание фабрики сессий
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();


        // Создание сессии
        try (Session session = sessionFactory.getCurrentSession()){
            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            Student student = Student.create();

            // Сохранение объекта в базе данных
            session.save(student);
            System.out.println("Object student save successfully");


            // Чтение объекта из базы данных
            Student retrievedStudent = session.get(Student.class, student.getId());
            System.out.println("Object student retrieved successfully");
            System.out.println("Retrieved student object: " + retrievedStudent);

            // Обновление объекта
            retrievedStudent.updateName();
            retrievedStudent.updateAge();
            session.update(retrievedStudent);
            System.out.println("Object student update successfully");


//            Student retrievedStudent2 = session.get(Student.class, 23);
//            // Удаление объекта
//            session.delete(retrievedStudent2);
//            System.out.println("Object student delete successfully");

            // Коммит транзакции
            session.getTransaction().commit();
            System.out.println("Transaction commit successfully");
        }
    }
}

