CREATE TABLE IF NOT EXISTS Anime (
                                     id SERIAL PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
                                    rating DOUBLE PRECISION NOT NULL,
    maincharacter VARCHAR(255) NOT NULL,
                                    description TEXT NOT NULL
    );

