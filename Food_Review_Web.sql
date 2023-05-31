CREATE TABLE roles(
 id INT PRIMARY KEY,
 roleName VARCHAR(50) NOT NULL
)

CREATE TABLE users (
  user_id INT PRIMARY KEY IDENTITY(1,1),
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  roleId INT REFERENCES roles(id),
  join_date DATE NOT NULL DEFAULT GETDATE()
)

CREATE TABLE categories(
 id INT PRIMARY KEY,
 name NVARCHAR(50) NOT NULL
)

CREATE TABLE foods (
  food_id INT PRIMARY KEY IDENTITY(1,1),
  food_name NVARCHAR(50) NOT NULL,
  image VARCHAR(255) NOT NULL,
  description NVARCHAR(200) NOT NULL,
  categoryId INT REFERENCES categories(id),
  avg_rating DECIMAL(2,1) DEFAULT 0.0
)

CREATE TABLE food_reviews (
  review_id INT PRIMARY KEY IDENTITY(1,1),
  user_id INT NOT NULL,
  food_id INT NOT NULL,
  review_text NVARCHAR(500) NOT NULL,
  review_date DATE NOT NULL DEFAULT GETDATE(),
  FOREIGN KEY (user_id) REFERENCES users(user_id),
  FOREIGN KEY (food_id) REFERENCES foods(food_id)
)

CREATE TABLE food_votes (
  vote_id INT PRIMARY KEY IDENTITY(1,1),
  user_id INT NOT NULL,
  review_id INT NOT NULL,
  rating int NOT NULL,
  vote_date DATE NOT NULL DEFAULT GETDATE(),
  FOREIGN KEY (user_id) REFERENCES users(user_id),
  FOREIGN KEY (review_id) REFERENCES food_reviews(review_id)
)

alter table food_votes
add food_id int references foods(food_id) not null


