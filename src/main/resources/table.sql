-- Создание таблиц
CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          start_date TIMESTAMP(6),
                          end_date TIMESTAMP(6),
                          total_cost NUMERIC(38, 2),
                          car_id BIGINT,
                          customer_id BIGINT
);

CREATE TABLE cars (
                      id BIGSERIAL PRIMARY KEY,
                      brand VARCHAR(255),
                      model VARCHAR(255),
                      rental_price NUMERIC(38, 2),
                      status VARCHAR(255),
                      year INT NOT NULL,
                      company_id BIGINT
);

CREATE TABLE customers (
                           id BIGSERIAL PRIMARY KEY,
                           contact_info VARCHAR(255),
                           name VARCHAR(255)
);

CREATE TABLE feedbacks (
                           id BIGSERIAL PRIMARY KEY,
                           rating INT NOT NULL,
                           comment VARCHAR(255),
                           booking_id BIGINT
);

CREATE TABLE insurances (
                            id BIGSERIAL PRIMARY KEY,
                            cost NUMERIC(38, 2),
                            insurance_type VARCHAR(255),
                            start_date TIMESTAMP(6),
                            end_date TIMESTAMP(6),
                            car_id BIGINT,
                            booking_id BIGINT
);

CREATE TABLE payments (
                          id BIGSERIAL PRIMARY KEY,
                          amount NUMERIC(38, 2),
                          payment_date TIMESTAMP(6),
                          payment_type VARCHAR(255),
                          booking_id BIGINT
);

CREATE TABLE rental_companies (
                                  id BIGSERIAL PRIMARY KEY,
                                  name VARCHAR(255),
                                  location VARCHAR(255),
                                  contact_info VARCHAR(255)
);
-- Вставка данных в таблицу rental_companies
INSERT INTO rental_companies (name, location, contact_info)
VALUES ('RentCo1', 'New York, NY', 'contact@rentco1.com'),
       ('RentCo2', 'Los Angeles, CA', 'contact@rentco2.com'),
       ('RentCo3', 'Chicago, IL', 'contact@rentco3.com'),
       ('RentCo4', 'Houston, TX', 'contact@rentco4.com'),
       ('RentCo5', 'Phoenix, AZ', 'contact@rentco5.com'),
       ('RentCo6', 'Philadelphia, PA', 'contact@rentco6.com'),
       ('RentCo7', 'San Antonio, TX', 'contact@rentco7.com'),
       ('RentCo8', 'San Diego, CA', 'contact@rentco8.com'),
       ('RentCo9', 'Dallas, TX', 'contact@rentco9.com'),
       ('RentCo10', 'San Jose, CA', 'contact@rentco10.com');

-- Вставка данных в таблицу cars
INSERT INTO cars (brand, model, rental_price, status, year, company_id)
VALUES ('Toyota', 'Corolla', 55.00, 'AVAILABLE', 2019, 1),
       ('Honda', 'Civic', 60.00, 'AVAILABLE', 2020, 2),
       ('Ford', 'Mustang', 120.00, 'AVAILABLE', 2018, 3),
       ('Chevrolet', 'Camaro', 130.00, 'UNDER_MAINTENANCE', 2017, 4),
       ('Tesla', 'Model S', 150.00, 'AVAILABLE', 2021, 5),
       ('BMW', 'X5', 140.00, 'AVAILABLE', 2016, 6),
       ('Audi', 'A4', 100.00, 'AVAILABLE', 2018, 7),
       ('Mercedes-Benz', 'C-Class', 105.00, 'AVAILABLE', 2019, 8),
       ('Hyundai', 'Elantra', 45.00, 'AVAILABLE', 2020, 9),
       ('Kia', 'Sorento', 85.00, 'AVAILABLE', 2021, 10);


-- Вставка данных в таблицу customers
INSERT INTO customers (contact_info, name)
VALUES ('john.doe@example.com', 'John Doe'),
       ('jane.doe@example.com', 'Jane Doe'),
       ('michael.smith@example.com', 'Michael Smith'),
       ('emily.johnson@example.com', 'Emily Johnson'),
       ('william.brown@example.com', 'William Brown'),
       ('olivia.williams@example.com', 'Olivia Williams'),
       ('james.jones@example.com', 'James Jones'),
       ('emma.garcia@example.com', 'Emma Garcia'),
       ('liam.martinez@example.com', 'Liam Martinez'),
       ('noah.rodriguez@example.com', 'Noah Rodriguez'),
       ('ava.lopez@example.com', 'Ava Lopez'),
       ('sophia.martin@example.com', 'Sophia Martin'),
       ('lucas.davis@example.com', 'Lucas Davis'),
       ('mason.hall@example.com', 'Mason Hall'),
       ('charlotte.walker@example.com', 'Charlotte Walker'),
       ('isabella.hernandez@example.com', 'Isabella Hernandez'),
       ('jackson.clark@example.com', 'Jackson Clark'),
       ('mia.mitchell@example.com', 'Mia Mitchell'),
       ('benjamin.rivera@example.com', 'Benjamin Rivera'),
       ('grace.torres@example.com', 'Grace Torres');


-- Установление внешних ключей
ALTER TABLE bookings ADD CONSTRAINT FK_Car_Booking FOREIGN KEY (car_id) REFERENCES cars(id);
ALTER TABLE bookings ADD CONSTRAINT FK_Customer_Booking FOREIGN KEY (customer_id) REFERENCES customers(id);

ALTER TABLE cars ADD CONSTRAINT FK_Company_Car FOREIGN KEY (company_id) REFERENCES rental_companies(id);

ALTER TABLE feedbacks ADD CONSTRAINT FK_Booking_Feedback FOREIGN KEY (booking_id) REFERENCES bookings(id);

ALTER TABLE insurances ADD CONSTRAINT FK_Booking_Insurance FOREIGN KEY (booking_id) REFERENCES bookings(id);
ALTER TABLE insurances ADD CONSTRAINT FK_Car_Insurance FOREIGN KEY (car_id) REFERENCES cars(id);

ALTER TABLE payments ADD CONSTRAINT FK_Booking_Payment FOREIGN KEY (booking_id) REFERENCES bookings(id);
