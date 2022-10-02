INSERT INTO public.companies (name, description) VALUES ('EPAM', 'Specialize in service development, digital platform engineering, and digital product design'),
('Luxoft', 'Global digital strategy and software engineering firm'),
('SoftServe', 'Provider of IT-services and Software Developing company');
INSERT INTO public.customers (name, description) VALUES ('OTP Bank', 'The OTP Bank is a leading banking group in Central and Eastern Europe'),
('Santander Bank', 'Santander Bank is part of Banco Santander, one of the largest financial groups in the world'),
('Credit Agricole Bank', 'Reference bank for companies in international trade and offers them a complete range of services');
INSERT INTO public.developers (first_name, last_name, age) VALUES ( 'Ivan','Kovalenko',25),
('Petro','Bondarenko',30),
('Sergii','Tkachenko',35),
('Anna','Shevchenko',25),
('Oksana','Koval',30),
('Juliya','Shvec',35);
INSERT INTO public.projects (name, description) VALUES ('ChatBot-application','Built-in chatBot for customers in application'),
('ChatBot-website','Built-in chatBot for customers on website'),
('Application','Application for customers'),
('Website','Website for customers'),
('Program for reports','Program to generate reports to Regulator'),
('Database','Database for storing customers data'),
('Program for HR','Program for HR to manage human resources');
INSERT INTO public.skills (area, level) VALUES ('Java','Junior'), ('Java','Middle'), ('Java','Senior'), ('C++','Junior'),
('C++','Middle'), ('C++','Senior'), ('C#','Junior'), ('C#','Middle'),
('C#','Senior'), ('JS','Junior'), ('JS','Middle'), ('JS','Senior');
INSERT INTO public.companies_projects (company_id, project_id) VALUES (1, 1), (1, 2), (2, 1), (2, 3), (3, 4), (3, 5), (1, 6), (2, 7);
INSERT INTO public.customers_projects (customer_id, project_id) VALUES (1, 1), (2, 2), (3, 3), (1, 4), (2, 5), (3, 6), (1, 7);
INSERT INTO public.developers_projects (developer_id, project_id) VALUES (1, 1), (2, 1), (3, 1), (1, 2), (2, 3), (3, 4), (4, 2), (5, 2), (6, 3), (4, 7), (5, 6), (6, 5);
INSERT INTO public.developers_skills (developer_id, skill_id) VALUES (1, 1), (1, 4), (2, 2), (2, 5), (3, 1), (3, 6), (4, 7), (4, 10), (5, 8), (5, 11), (6, 9), (6, 12);

UPDATE public.developers SET salary = 1000 WHERE id = 1;
UPDATE public.developers SET salary = 2000 WHERE id = 2;
UPDATE public.developers SET salary = 3000 WHERE id = 3;
UPDATE public.developers SET salary = 4000 WHERE id = 4;
UPDATE public.developers SET salary = 5000 WHERE id = 5;
UPDATE public.developers SET salary = 6000 WHERE id = 6;

UPDATE public.projects SET start_date = '2022-01-01' WHERE id = 1;
UPDATE public.projects SET start_date = '2022-02-01' WHERE id = 2;
UPDATE public.projects SET start_date = '2022-03-01' WHERE id = 3;
UPDATE public.projects SET start_date = '2022-04-01' WHERE id = 4;
UPDATE public.projects SET start_date = '2022-05-01' WHERE id = 5;
UPDATE public.projects SET start_date = '2022-06-01' WHERE id = 6;
UPDATE public.projects SET start_date = '2022-07-01' WHERE id = 7;






