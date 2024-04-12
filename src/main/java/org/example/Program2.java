package org.example;

import org.example.models.Course;
import org.example.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Program2 {
    /**
     * Создайте базу данных (например, SchoolDB).
     * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
     * Настройте Hibernate для работы с вашей базой данных.
     * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
     * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
     * Убедитесь, что каждая операция выполняется в отдельной транзакции.
     */
    public static void main(String[] args) {
        // Создание фабрики сессий
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Создание сессии
        try (Session session = sessionFactory.getCurrentSession()) {
            // Начало транзакции
            session.beginTransaction();

            // Создание объекта Course
            Course course = new Course();
            course.setTitle("Java GB");
            course.setDuration(30);

            // Сохранение объекта в базе данных
            session.save(course);
            System.out.println("Course object saved successfully");

            // Чтение объекта из базы данных
            Course retrievedCourse = session.get(Course.class, course.getId());
            System.out.println("Course object retrieved successfully");
            System.out.println("Retrieved course object: " + retrievedCourse);

            // Обновление объекта
            retrievedCourse.setDuration(45);
            session.update(retrievedCourse);
            System.out.println("Course object updated successfully");

            // Удаление объекта
//            session.delete(retrievedCourse);
//            System.out.println("Course object deleted successfully");

            // Коммит транзакции
            session.getTransaction().commit();
            System.out.println("Transaction committed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close(); // Закрытие фабрики сессий
        }
    }
}