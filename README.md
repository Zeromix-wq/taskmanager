- `GET /api/tasks?period=TODAY|WEEK|MONTH&completed=true|false` — получить список
- `POST /api/tasks` — создать задачу
- `PUT /api/tasks/{id}` — изменить задачу
- `PATCH /api/tasks/{id}/status` — изменить статус
- `DELETE /api/tasks/{id}` — удалить задачу

# Запуск
./mvnw spring-boot:run

# Тесты
./mvnw test