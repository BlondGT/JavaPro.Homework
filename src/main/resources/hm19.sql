CREATE TABLE Homework
(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(300) NOT NULL
);

CREATE TABLE Lesson
(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    updated_at TIMESTAMP DEFAULT NOW(),
    homework_id INTEGER REFERENCES Homework(id)
);

CREATE TABLE Schedule
(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE Lesson_and_Schedule
(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    lesson_id INTEGER REFERENCES Lesson(id),
    schedule_id INTEGER REFERENCES Schedule(id)
);
