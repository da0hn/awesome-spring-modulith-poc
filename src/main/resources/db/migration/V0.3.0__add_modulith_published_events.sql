CREATE TABLE IF NOT EXISTS event_publication
(
    id               UUID PRIMARY KEY,
    publication_date TIMESTAMP,
    listener_id      VARCHAR(255),
    event_type       VARCHAR(255),
    serialized_event TEXT,
    completion_date  TIMESTAMP
);
