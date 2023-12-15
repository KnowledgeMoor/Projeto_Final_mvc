create database dbCrud;

use dbCrud;

create table tb_login(

usuario varchar(255) primary key,
senha varchar(255)
);

create table tb_role (

id integer auto_increment,
nome varchar(255) not null,
primary key(id)
);

insert into tb_role(nome) values ('ADMIN');

insert into tb_role(nome) values ('USER');

create table tb_role_user (

usuario varchar(255) not null,

role_id integer not null,

primary key(usuario,role_id),

foreign key(usuario) references tb_login(usuario),

foreign key(role_id) references tb_role(id)

);

--insert into tb_role_user(usuario,role_id) values ('gabriel',1);
--insert into tb_role_user(usuario,role_id) values ('jojo',2);

CREATE TABLE Paciente (
    code_paciente INT PRIMARY KEY auto_increment,
    nome VARCHAR(255),
    data_nasc DATE,
    sexo VARCHAR(255),
    endereco VARCHAR(255),
    telefone VARCHAR(15),
    cpf VARCHAR(255)
);

CREATE TABLE Medico (
    cod_medico INT PRIMARY KEY auto_increment,
    nome VARCHAR(255),
    especialidade VARCHAR(255),
    num_RM VARCHAR(20),
    telefone VARCHAR(15)
);

CREATE TABLE Sala (
    cod_sala INT PRIMARY KEY auto_increment,
    num_sala INT,
    status_ocupacao VARCHAR(20)
);

CREATE TABLE Consulta (
    cod_consulta INT PRIMARY KEY auto_increment,
    cod_paciente INT,
    cod_medico INT,
    status VARCHAR(20),
    data_hora DATETIME,
    cod_sala INT,
    FOREIGN KEY (cod_paciente) REFERENCES Paciente(code_paciente),
    FOREIGN KEY (cod_medico) REFERENCES Medico(cod_medico),
    FOREIGN KEY (cod_sala) REFERENCES Sala(cod_sala)
);

CREATE TABLE Medicamentos (
    cod_meds INT PRIMARY KEY auto_increment,
    meds_prescritos TEXT,
    dosagens TEXT,
    cod_consulta INT,
    FOREIGN KEY (cod_consulta) REFERENCES Consulta(cod_consulta)
);

CREATE TABLE ExameMedico (
    cod_exame INT PRIMARY KEY auto_increment,
    tipo_exame VARCHAR(255),
    data_hora DATETIME,
    resultados TEXT,
    cod_consulta INT,
    cod_sala INT,
    FOREIGN KEY (cod_consulta) REFERENCES Consulta(cod_consulta),
    FOREIGN KEY (cod_sala) REFERENCES Sala(cod_sala)
);

INSERT INTO Paciente (nome, data_nasc, sexo, endereco, telefone, cpf)
VALUES
    ('Maria Silva', '1990-05-15', 'F', '123 Main St', '123456789', '12345678901'),
    ('John Doe', '1985-09-20', 'M', '456 Elm St', '987654321', '98765432109'),
    ('Sophia Johnson', '1992-11-30', 'F', '789 Maple St', '111222333', '11122233307'),
    ('Daniel Brown', '1978-07-08', 'M', '321 Oak St', '444555666', '44455566604'),
    ('Isabella Martinez', '1989-04-25', 'F', '555 Pine St', '777888999', '77788899902'),
    ('Ethan Wilson', '1980-12-12', 'M', '222 Cedar St', '333444555', '33344455506'),
    ('Olivia Taylor', '1995-03-18', 'F', '888 Birch St', '666777888', '66677788803'),
    ('Liam Garcia', '1975-06-22', 'M', '777 Elm St', '999000111', '99900011100'),
    ('Ava Anderson', '1998-08-05', 'F', '999 Oak St', '222333444', '22233344408'),
    ('Noah Rodriguez', '1993-09-10', 'M', '444 Maple St', '555666777', '55566677705'),
    ('Mia Hernandez', '1987-02-14', 'F', '111 Pine St', '888999000', '88899900001'),
    ('William Smith', '1983-01-03', 'M', '666 Cedar St', '333222111', '33322211109'),
    ('Emily Wilson', '2000-10-20', 'F', '333 Birch St', '777888999', '77788899904'),
    ('James Taylor', '1991-12-28', 'M', '222 Elm St', '555444333', '55544433306'),
    ('Emma Brown', '1982-07-07', 'F', '999 Oak St', '111222333', '11122233300');

INSERT INTO Medico (nome, especialidade, num_RM, telefone)
VALUES
    ('Dr. Garcia', 'Cardiology', 'RM1234', '111222333'),
    ('Dr. Patel', 'Pediatrics', 'RM5678', '444555666'),
    ('Dr. Nguyen', 'Oncology', 'RM9012', '777888999'),
    ('Dr. Kim', 'Orthopedics', 'RM3456', '222333444'),
    ('Dr. Santos', 'Dermatology', 'RM7890', '555666777'),
    ('Dr. Lee', 'Neurology', 'RM2345', '888999000'),
    ('Dr. Garcia', 'Psychiatry', 'RM6789', '333444555'),
    ('Dr. Fernandez', 'Gastroenterology', 'RM8901', '666777888'),
    ('Dr. Khan', 'Endocrinology', 'RM4567', '999000111'),
    ('Dr. Chen', 'Ophthalmology', 'RM1122', '123456789'),
    ('Dr. Wilson', 'Urology', 'RM3344', '987654321'),
    ('Dr. Rivera', 'Pulmonology', 'RM5566', '234567890'),
    ('Dr. Patel', 'Rheumatology', 'RM7788', '876543210'),
    ('Dr. Kim', 'Hematology', 'RM9900', '345678901'),
    ('Dr. Wang', 'Allergy and Immunology', 'RM0011', '654321098');

INSERT INTO Sala (num_sala, status_ocupacao)
VALUES
    (101, 'Occupied'),
    (102, 'Available'),
    (103, 'Available'),
    (104, 'Occupied'),
    (105, 'Available'),
    (106, 'Occupied'),
    (107, 'Available'),
    (108, 'Occupied'),
    (109, 'Available'),
    (110, 'Occupied'),
    (111, 'Available'),
    (112, 'Occupied'),
    (113, 'Available'),
    (114, 'Occupied'),
    (115, 'Available');

INSERT INTO Consulta (cod_paciente, cod_medico, status, data_hora, cod_sala)
VALUES
    (1, 1, 'Scheduled', '2023-01-10 09:00:00', 1),
    (2, 2, 'Completed', '2023-02-15 10:30:00', 2),
    (3, 3, 'Scheduled', '2023-03-20 11:00:00', 3),
    (4, 4, 'Completed', '2023-04-25 13:45:00', 4),
    (5, 5, 'Scheduled', '2023-05-10 15:30:00', 5),
    (6, 6, 'Completed', '2023-06-18 08:00:00', 6),
    (7, 7, 'Scheduled', '2023-07-05 10:15:00', 7),
    (8, 8, 'Completed', '2023-08-20 14:30:00', 8),
    (9, 9, 'Scheduled', '2023-09-15 16:00:00', 9),
    (10, 10, 'Completed', '2023-10-10 09:45:00', 10),
    (11, 11, 'Scheduled', '2023-11-18 11:30:00', 11),
    (12, 12, 'Completed', '2023-12-05 13:00:00', 12),
    (13, 13, 'Scheduled', '2024-01-22 14:45:00', 13),
    (14, 14, 'Completed', '2024-02-28 16:30:00', 14),
    (15, 15, 'Scheduled', '2024-03-15 08:15:00', 15),
    (1, 3, 'Scheduled', '2023-04-08 09:30:00', 2),
    (2, 4, 'Completed', '2023-05-20 11:45:00', 3),
    (3, 5, 'Scheduled', '2023-06-12 14:00:00', 4),
    (4, 6, 'Completed', '2023-07-30 16:15:00', 5),
    (5, 7, 'Scheduled', '2023-08-25 08:45:00', 6),
    (6, 8, 'Completed', '2023-09-18 10:00:00', 7),
    (7, 9, 'Scheduled', '2023-10-12 12:30:00', 8),
    (8, 10, 'Completed', '2023-11-05 14:45:00', 9),
    (9, 11, 'Scheduled', '2023-12-03 16:00:00', 10),
    (10, 12, 'Completed', '2024-01-20 09:30:00', 11),
    (11, 13, 'Scheduled', '2024-02-14 11:45:00', 12),
    (12, 14, 'Completed', '2024-03-10 14:00:00', 13),
    (13, 15, 'Scheduled', '2024-04-06 16:15:00', 14),
    (14, 1, 'Completed', '2024-05-02 08:45:00', 15),
    (15, 2, 'Scheduled', '2024-06-20 10:00:00', 1);

INSERT INTO ExameMedico (tipo_exame, data_hora, resultados, cod_consulta, cod_sala)
VALUES
    ('Blood Test', '2023-01-12 11:00:00', 'Normal', 1, 1),
    ('X-Ray', '2023-02-18 13:45:00', 'No abnormalities found', 2, 2),
    ('MRI', '2023-03-20 09:30:00', 'Normal brain scan', 3, 3),
    ('Ultrasound', '2023-04-25 10:15:00', 'Normal', 4, 4),
    ('CT Scan', '2023-05-10 14:00:00', 'No issues detected', 5, 5),
    ('EKG', '2023-06-18 12:30:00', 'Stable heart rhythm', 6, 6),
    ('Endoscopy', '2023-07-05 09:00:00', 'Mild inflammation', 7, 7),
    ('Colonoscopy', '2023-08-20 11:45:00', 'Polyps detected', 8, 8),
    ('Bone Density Test', '2023-09-15 10:00:00', 'Normal bone density', 9, 9),
    ('Allergy Test', '2023-10-10 13:20:00', 'Allergic to pollen', 10, 10),
    ('Skin Biopsy', '2023-11-18 15:30:00', 'Benign mole', 11, 11),
    ('Pulmonary Function Test', '2023-12-05 08:45:00', 'Normal lung function', 12, 12),
    ('Thyroid Function Test', '2024-01-22 09:45:00', 'Hormone levels normal', 13, 13),
    ('Liver Function Test', '2024-02-28 11:00:00', 'Healthy liver function', 14, 14),
    ('Stress Test', '2024-03-15 14:15:00', 'Good cardiovascular response', 15, 15),
    ('Blood Test', '2023-04-12 10:00:00', 'Elevated levels', 16, 2),
    ('X-Ray', '2023-05-25 12:15:00', 'Fracture detected', 17, 3),
    ('MRI', '2023-06-20 14:30:00', 'Mild abnormalities found', 18, 4),
    ('Ultrasound', '2023-07-10 16:45:00', 'Normal', 19, 5),
    ('CT Scan', '2023-08-05 09:00:00', 'No significant issues', 20, 6),
    ('EKG', '2023-09-02 11:30:00', 'Irregular heart rhythm', 21, 7),
    ('Endoscopy', '2023-10-01 13:45:00', 'Inflammation observed', 22, 8),
    ('Colonoscopy', '2023-11-08 15:00:00', 'Polyps removed', 23, 9),
    ('Bone Density Test', '2023-12-15 08:30:00', 'Low bone density', 24, 10),
    ('Allergy Test', '2024-01-10 10:45:00', 'Multiple allergies detected', 25, 11),
    ('Skin Biopsy', '2024-02-18 12:00:00', 'Suspicious mole', 26, 12),
    ('Pulmonary Function Test', '2024-03-14 14:15:00', 'Reduced lung capacity', 27, 13),
    ('Thyroid Function Test', '2024-04-09 16:30:00', 'Irregular hormone levels', 28, 14),
    ('Liver Function Test', '2024-05-05 09:00:00', 'Elevated liver enzymes', 29, 15),
    ('Stress Test', '2024-06-22 11:15:00', 'Positive stress response', 30, 1);


INSERT INTO Medicamentos (meds_prescritos, dosagens, cod_consulta)
VALUES
    ('Medicine A', '10mg', 1),
    ('Medicine B', '5mg', 2),
    ('Medicine C', '15mg', 3),
    ('Medicine D', '20mg', 4),
    ('Medicine E', '7.5mg', 5),
    ('Medicine F', '12.5mg', 6),
    ('Medicine G', '25mg', 7),
    ('Medicine H', '30mg', 8),
    ('Medicine I', '9mg', 9),
    ('Medicine J', '22.5mg', 10),
    ('Medicine K', '17.5mg', 11),
    ('Medicine L', '8mg', 12),
    ('Medicine M', '11mg', 13),
    ('Medicine N', '14mg', 14),
    ('Medicine O', '18mg', 15);
    ('Medicine P', '25mg', 16),
    ('Medicine Q', '30mg', 17),
    ('Medicine R', '12.5mg', 18),
    ('Medicine S', '18mg', 19),
    ('Medicine T', '15mg', 20),
    ('Medicine U', '20mg', 21),
    ('Medicine V', '10mg', 22),
    ('Medicine W', '7.5mg', 23),
    ('Medicine X', '5mg', 24),
    ('Medicine Y', '22.5mg', 25),
    ('Medicine Z', '8mg', 26),
    ('Medicine AA', '11mg', 27),
    ('Medicine BB', '14mg', 28),
    ('Medicine CC', '17.5mg', 29),
    ('Medicine DD', '9mg', 30);
