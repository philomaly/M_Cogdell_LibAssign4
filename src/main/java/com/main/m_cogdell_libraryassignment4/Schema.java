package com.main.m_cogdell_libraryassignment4;

/*
 * This class is used to create the schema and initialize the Database with values from the previous assignments
 * It keeps me from having to manually type everything
 */
class Schema {
    public String schema = "CREATE TABLE Authors (\n" +
            "\tauthorID INT PRIMARY KEY,\n" +
            "\tfullName VARCHAR(25),\n" +
            "\tnationality CHAR(30),\n" +
            "\tbirthYear INT\n" +
            ");\n" +
            "ALTER TABLE Authors ALTER COLUMN fullName TYPE VARCHAR(50);\n" +
            "CREATE TABLE Books (\n" +
            "\tbookID INT PRIMARY KEY,\n" +
            "\ttitle VARCHAR(50),\n" +
            "\tauthorID INT,\n" +
            "\tgenre VARCHAR(50),\n" +
            "\tpublicationYear INT,\n" +
            "\tISBN VARCHAR(17),\n" +
            "\tavailableCopies INT\n" +
            ");\n" +
            "ALTER TABLE Books ADD FOREIGN KEY (authorID) REFERENCES Authors (authorID);\n" +
            "CREATE TABLE Members (\n" +
            "\tmemberID INT PRIMARY KEY,\n" +
            "\tfullName VARCHAR(25),\n" +
            "\temail VARCHAR(25),\n" +
            "\tphone CHAR(14),\n" +
            "\tjoinDate DATE,\n" +
            "\tactiveStatus BOOLEAN\n" +
            ");\n" +
            "ALTER TABLE Members ALTER COLUMN fullName TYPE VARCHAR(50);\n" +
            "ALTER TABLE Members ALTER COLUMN email TYPE VARCHAR(50);\n" +
            "ALTER TABLE Members ALTER COLUMN phone TYPE VARCHAR(20);\n" +
            "CREATE TABLE Borrowings (\n" +
            "\tmemberID INT, \n" +
            "\tbookID INT,\n" +
            "\tborrowDate DATE,\n" +
            "\tdueDate DATE,\n" +
            "\treturnDate DATE,\n" +
            "\tPRIMARY KEY (memberID, bookID, borrowDate),\n" +
            "\tFOREIGN KEY (memberID) REFERENCES Members (memberID)\n" +
            ");\n" +
            "ALTER TABLE Borrowings ADD FOREIGN KEY (bookID) REFERENCES Books (bookID);\n" +
            "CREATE TABLE Fines (\n" +
            "\tfineID INT PRIMARY KEY,\n" +
            "\tmemberID INT,\n" +
            "\tbookID INT,\n" +
            "\tborrowDate DATE,\n" +
            "\tamount NUMERIC(5,2),\n" +
            "\tpaidStatus BOOLEAN,\n" +
            "\tpaymentDueDate DATE\n" +
            ");\n" +
            "ALTER TABLE Fines ALTER COLUMN amount TYPE NUMERIC(10,2);\n" +
            "CREATE TABLE Payments (\n" +
            "\tpaymentID INT PRIMARY KEY,\n" +
            "\tfineID INT,\n" +
            "\tmemberID INT,\n" +
            "\tpaymentDate DATE,\n" +
            "\tmethod VARCHAR(30)\n" +
            ");\n" +
            "ALTER TABLE Payments ADD FOREIGN KEY (fineID) REFERENCES Fines (fineID);\n" +
            "ALTER TABLE Payments ADD FOREIGN KEY (memberID) REFERENCES Members (memberID);\n" +
            "ALTER TABLE Payments ADD amountPaid NUMERIC(10,2);\n" +
            "ALTER TABLE Fines ADD FOREIGN KEY (memberID, bookID, borrowDate)\n" +
            "REFERENCES Borrowings (memberID, bookID, borrowDate);";
    public String initialInserts = "INSERT INTO Authors (authorID, fullName, nationality, birthYear) VALUES\n" +
            "\t(1, 'George Orwell', 'British', 1903),\n" +
            "\t(2, 'Jane Austen', 'British', 1775),\n" +
            "\t(3, 'Mark Twain', 'American', 1835),\n" +
            "\t(4, 'J.K. Rowling', 'British', 1965);\n" +
            "INSERT INTO Authors (authorID, fullName, nationality, birthYear) VALUES\n" +
            "\t(5, 'Ernest Hemingway', 'American', 1899),\n" +
            "\t(6, 'Haruki Murakami', 'Japanese', 1949),\n" +
            "\t(7, 'Leo Tolstoy', 'Russian', 1828),\n" +
            "\t(8, 'F. Scott Fitzgerald', 'American', 1896),\n" +
            "\t(9, 'Agatha Christie', 'British', 1890),\n" +
            "\t(10, 'Gabriel Garcia Marquez', 'Colombian', 1927);\n" +
            "INSERT INTO Books (bookID, title, authorID, genre, publicationYear, ISBN, availableCopies) VALUES\n" +
            "\t(101, '1984', 1, 'Dystopian', 1949, '9780451524935', 5);\n" +
            "INSERT INTO Books (bookID, title, authorID, genre, publicationYear, ISBN, availableCopies) VALUES\n" +
            "\t(102, 'Pride and Prejudice', 2, 'Romance', 1813, '9780141439518', 5),\n" +
            "\t(103, 'Adventures of Huckleberry Fin', 3, 'Adventure', 1884, '9780486280615', 3),\n" +
            "\t(104, 'Harry Potter and the Sorcerer''s Stone', 4, 'Fantasy', 1997, '978055900353427', 10),\n" +
            "\t(105, 'The Old Man and The Sea', 5, 'Fiction', 1952, '9780684801223', 2),\n" +
            "\t(106, 'Norwegian Wood', 6, 'Romance', 1987, '9780375705024', 4),\n" +
            "\t(107, 'War and Peace', 7, 'Histotrical Fiction', 1869, '9780199232765', 1),\n" +
            "\t(108, 'The Great Gatsby', 8, 'Fiction', 1925, '9780743273565', 6),\n" +
            "\t(109, 'Murder on the Orient Express', 9, 'Mystery', 1934, '9780062073495', 5),\n" +
            "\t(110, 'One Hundred Years of Solitude', 10, 'Magical Realism', 1967, '9780060883287', 3);\n" +
            "INSERT INTO Members (memberID, fullName, email, phone, joinDate, activeStatus) VALUES\n" +
            "\t(201, 'Alice Johnson', 'alice.j@example.com', '1234567890', '2021-01-15', TRUE),\n" +
            "\t(202, 'Bob Smith', 'bob.smith@example.com', '1231231234', '2021-6-10', TRUE),\n" +
            "\t(203, 'Catherine Lee', 'catherine.lee@example.com', '2345678901', '2022-03-01', TRUE),\n" +
            "\t(204, 'David Kim', 'david.kim@example.com', '3456789012', '2020-11-20', FALSE),\n" +
            "\t(205, 'Emily Davis', 'emily.d@example.com', '4567890123', '2021-09-25', TRUE),\n" +
            "\t(206, 'Frank Turner', 'frank.t@example.com', '5678901234', '2022-01-15', TRUE),\n" +
            "\t(207, 'Grace Hall', 'grace.h@example.com', '6789012345', '2021-04-18', FALSE),\n" +
            "\t(208, 'Henry Brown', 'henry.b@example.com', '7890123456', '2023-02-01', TRUE),\n" +
            "\t(209, 'Isabella King', 'isabella.k@example.com', '8901234567', '2022-05-10', TRUE),\n" +
            "\t(210, 'Jack White', 'jack.w@example.com', '9012345678', '2021-12-12', TRUE);\n" +
            "INSERT INTO Borrowings (memberID, bookID, borrowDate, dueDate, returnDate) VALUES\n" +
            "\t(201, 101, '2023-01-10', '2023-01-24', '2023-01-23'),\n" +
            "\t(202, 104, '2023-01-15', '2023-01-29', '2023-02-01'),\n" +
            "\t(203, 105, '2023-02-05', '2023-02-19', NULL),\n" +
            "\t(204, 102, '2023-02-10', '2023-02-24', '2023-02-25'),\n" +
            "\t(205, 108, '2023-03-01', '2023-03-15', NULL),\n" +
            "\t(206, 109, '2023-03-10', '2023-03-24', '2023-03-20'),\n" +
            "\t(207, 103, '2023-04-01', '2023-04-15', '2023-04-16'),\n" +
            "\t(208, 110, '2023-04-05', '2023-04-19', NULL),\n" +
            "\t(209, 106, '2023-05-10', '2023-04-05', '2023-05-23'),\n" +
            "\t(210, 107, '2023-06-01', '2023-06-15', NULL);\n" +
            "INSERT INTO Fines (fineID, memberID, bookID, borrowDate, amount, paidStatus, paymentDueDate) VALUES\n" +
            "\t(301, 202, 104, '2023-01-15', 5.00, TRUE, '2023-02-10'),\n" +
            "\t(302, 204, 102, '2023-02-10', 2.50, TRUE, '2023-03-01'),\n" +
            "\t(303, 205, 108, '2023-03-01', 3.00, FALSE, '2023-04-01'),\n" +
            "\t(304, 207, 103, '2023-04-01', 1.50, TRUE, '2023-04-20'),\n" +
            "\t(305, 210, 107, '2023-06-01', 6.00, FALSE, '2023-06-20');\n" +
            "INSERT INTO Payments (paymentID, fineID, memberID, paymentDate, method, amountPaid) VALUES\n" +
            "\t(401, 301, 202, '2023-05-05', 'Card', 5.00),\n" +
            "\t(402, 302, 204, '2023-02-28', 'Cash', 2.50),\n" +
            "\t(403, 304, 207, '2023-04-15', 'Card', 1.50);";

    public Schema(){}

    public String getSchema() { return schema; }
    public String getInitialInserts() { return initialInserts; }
}
