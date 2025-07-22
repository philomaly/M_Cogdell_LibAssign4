CREATE TABLE Authors (
	authorID INT PRIMARY KEY,
	fullName VARCHAR(25),
	nationality CHAR(30),
	birthYear INT
);
ALTER TABLE Authors ALTER COLUMN fullName TYPE VARCHAR(50);
CREATE TABLE Books (
	bookID INT PRIMARY KEY,
	title VARCHAR(50),
	authorID INT,
	genre VARCHAR(50),
	publicationYear INT,
	ISBN VARCHAR(17),
	availableCopies INT
);
ALTER TABLE Books ADD FOREIGN KEY (authorID) REFERENCES Authors (authorID);
CREATE TABLE Members (
	memberID INT PRIMARY KEY,
	fullName VARCHAR(25),
	email VARCHAR(25),
	phone CHAR(14),
	joinDate DATE,
	activeStatus BOOLEAN
);
ALTER TABLE Members ALTER COLUMN fullName TYPE VARCHAR(50);
ALTER TABLE Members ALTER COLUMN email TYPE VARCHAR(50);
ALTER TABLE Members ALTER COLUMN phone TYPE VARCHAR(20);
CREATE TABLE Borrowings (
	memberID INT, 
	bookID INT,
	borrowDate DATE,
	dueDate DATE,
	returnDate DATE,
	PRIMARY KEY (memberID, bookID, borrowDate),
	FOREIGN KEY (memberID) REFERENCES Members (memberID)
);
ALTER TABLE Borrowings ADD FOREIGN KEY (bookID) REFERENCES Books (bookID);
CREATE TABLE Fines (
	fineID INT PRIMARY KEY,
	memberID INT,
	bookID INT,
	borrowDate DATE,
	amount NUMERIC(5,2),
	paidStatus BOOLEAN,
	paymentDueDate DATE
);
ALTER TABLE Fines ALTER COLUMN amount TYPE NUMERIC(10,2);
CREATE TABLE Payments (
	paymentID INT PRIMARY KEY,
	fineID INT,
	memberID INT,
	paymentDate DATE,
	method VARCHAR(30)
);
ALTER TABLE Payments ADD FOREIGN KEY (fineID) REFERENCES Fines (fineID);
ALTER TABLE Payments ADD FOREIGN KEY (memberID) REFERENCES Members (memberID);
ALTER TABLE Payments ADD amountPaid NUMERIC(10,2);
ALTER TABLE Fines ADD FOREIGN KEY (memberID, bookID, borrowDate)
REFERENCES Borrowings (memberID, bookID, borrowDate);
INSERT INTO Authors (authorID, fullName, nationality, birthYear) VALUES
	(1, 'George Orwell', 'British', 1903),
	(2, 'Jane Austen', 'British', 1775),
	(3, 'Mark Twain', 'American', 1835),
	(4, 'J.K. Rowling', 'British', 1965);
INSERT INTO Authors (authorID, fullName, nationality, birthYear) VALUES
	(5, 'Ernest Hemingway', 'American', 1899),
	(6, 'Haruki Murakami', 'Japanese', 1949),
	(7, 'Leo Tolstoy', 'Russian', 1828),
	(8, 'F. Scott Fitzgerald', 'American', 1896),
	(9, 'Agatha Christie', 'British', 1890),
	(10, 'Gabriel Garcia Marquez', 'Colombian', 1927);
INSERT INTO Books (bookID, title, authorID, genre, publicationYear, ISBN, availableCopies) VALUES
	(101, '1984', 1, 'Dystopian', 1949, '9780451524935', 5);
INSERT INTO Books (bookID, title, authorID, genre, publicationYear, ISBN, availableCopies) VALUES
	(102, 'Pride and Prejudice', 2, 'Romance', 1813, '9780141439518', 5),
	(103, 'Adventures of Huckleberry Fin', 3, 'Adventure', 1884, '9780486280615', 3),
	(104, 'Harry Potter and the Sorcerer''s Stone', 4, 'Fantasy', 1997, '978055900353427', 10),
	(105, 'The Old Man and The Sea', 5, 'Fiction', 1952, '9780684801223', 2),
	(106, 'Norwegian Wood', 6, 'Romance', 1987, '9780375705024', 4),
	(107, 'War and Peace', 7, 'Histotrical Fiction', 1869, '9780199232765', 1),
	(108, 'The Great Gatsby', 8, 'Fiction', 1925, '9780743273565', 6),
	(109, 'Murder on the Orient Express', 9, 'Mystery', 1934, '9780062073495', 5),
	(110, 'One Hundred Years of Solitude', 10, 'Magical Realism', 1967, '9780060883287', 3);
INSERT INTO Members (memberID, fullName, email, phone, joinDate, activeStatus) VALUES
	(201, 'Alice Johnson', 'alice.j@example.com', '1234567890', '2021-01-15', TRUE),
	(202, 'Bob Smith', 'bob.smith@example.com', '1231231234', '2021-6-10', TRUE),
	(203, 'Catherine Lee', 'catherine.lee@example.com', '2345678901', '2022-03-01', TRUE),
	(204, 'David Kim', 'david.kim@example.com', '3456789012', '2020-11-20', FALSE),
	(205, 'Emily Davis', 'emily.d@example.com', '4567890123', '2021-09-25', TRUE),
	(206, 'Frank Turner', 'frank.t@example.com', '5678901234', '2022-01-15', TRUE),
	(207, 'Grace Hall', 'grace.h@example.com', '6789012345', '2021-04-18', FALSE),
	(208, 'Henry Brown', 'henry.b@example.com', '7890123456', '2023-02-01', TRUE),
	(209, 'Isabella King', 'isabella.k@example.com', '8901234567', '2022-05-10', TRUE),
	(210, 'Jack White', 'jack.w@example.com', '9012345678', '2021-12-12', TRUE);
INSERT INTO Borrowings (memberID, bookID, borrowDate, dueDate, returnDate) VALUES
	(201, 101, '2023-01-10', '2023-01-24', '2023-01-23'),
	(202, 104, '2023-01-15', '2023-01-29', '2023-02-01'),
	(203, 105, '2023-02-05', '2023-02-19', NULL),
	(204, 102, '2023-02-10', '2023-02-24', '2023-02-25'),
	(205, 108, '2023-03-01', '2023-03-15', NULL),
	(206, 109, '2023-03-10', '2023-03-24', '2023-03-20'),
	(207, 103, '2023-04-01', '2023-04-15', '2023-04-16'),
	(208, 110, '2023-04-05', '2023-04-19', NULL),
	(209, 106, '2023-05-10', '2023-04-05', '2023-05-23'),
	(210, 107, '2023-06-01', '2023-06-15', NULL);
INSERT INTO Fines (fineID, memberID, bookID, borrowDate, amount, paidStatus, paymentDueDate) VALUES
	(301, 202, 104, '2023-01-15', 5.00, TRUE, '2023-02-10'),
	(302, 204, 102, '2023-02-10', 2.50, TRUE, '2023-03-01'),
	(303, 205, 108, '2023-03-01', 3.00, FALSE, '2023-04-01'),
	(304, 207, 103, '2023-04-01', 1.50, TRUE, '2023-04-20'),
	(305, 210, 107, '2023-06-01', 6.00, FALSE, '2023-06-20');
INSERT INTO Payments (paymentID, fineID, memberID, paymentDate, method, amountPaid) VALUES
	(401, 301, 202, '2023-05-05', 'Card', 5.00),
	(402, 302, 204, '2023-02-28', 'Cash', 2.50),
	(403, 304, 207, '2023-04-15', 'Card', 1.50);
