**1. Who checked out the book 'The Hobbit’?**

```sql
SELECT member.name
FROM member, book, checkout_item
WHERE checkout_item.member_id = member.id
  AND checkout_item.book_id = book.id
  AND book.title = 'The Hobbit';
```

- Answer: `Anand Beck`

**2. How many people have not checked out anything?**

```sql
SELECT count(*)
FROM member
WHERE member.id NOT IN (
  SELECT checkout_item.member_id
  FROM checkout_item
);
```

- Answer: `37`

**3. What books and movies aren't checked out?**

```sql
SELECT book.title
FROM book
WHERE book.id NOT IN (
  SELECT book_id FROM checkout_item WHERE book_id IS NOT NULL
)
UNION
SELECT movie.title
FROM movie
WHERE movie.id NOT IN (
  SELECT movie_id FROM checkout_item WHERE movie_id IS NOT NULL
);

```

- Answer: 
```
1984
Catcher in the Rye
Crouching Tiger, Hidden Dragon
Domain Driven Design
Fellowship of the Ring
Lawrence of Arabia
Office Space
Thin Red Line
To Kill a Mockingbird
Tom Sawyer
```

**4. Add the book 'The Pragmatic Programmer', and add yourself as a member. Check out 'The Pragmatic Programmer'. Use your query from question 1 to verify that you have checked it out. Also, provide the SQL used to update the database.**

```sql
INSERT INTO book (id, title) VALUES (100, 'The Pragmatic Programmer');
INSERT INTO member (id, name) VALUES (101, 'Daniel Bastos');
INSERT INTO checkout_item (member_id, book_id) VALUES (101, 100);
```

**5. Who has checked out more than 1 item? Tip: Research the GROUP BY syntax.**

```sql
SELECT member.name
FROM member, checkout_item
WHERE checkout_item.member_id = member.id
GROUP BY member.name
HAVING count(member.name) > 1;
```

- Answer:
```
Anand Beck
Frank Smith
```

