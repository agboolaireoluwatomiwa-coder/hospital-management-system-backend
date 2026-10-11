-- Sample data so the app is not empty on first run (skipped if the rows already exist)
INSERT INTO doctors (first_name, last_name, specialization, department, phone, email, license_number) VALUES
  ('Amina',  'Okafor',  'Cardiology',       'Cardiology',  '+234 801 000 0001', 'amina.okafor@hospital.test',  'MDCN-10001'),
  ('David',  'Adeyemi', 'General Practice', 'Outpatient',  '+234 801 000 0002', 'david.adeyemi@hospital.test',  'MDCN-10002'),
  ('Grace',  'Eze',     'Paediatrics',      'Children''s Ward', '+234 801 000 0003', 'grace.eze@hospital.test', 'MDCN-10003')
ON CONFLICT DO NOTHING;

INSERT INTO patients (first_name, last_name, gender, date_of_birth, phone, email, address, blood_group) VALUES
  ('Tunde',  'Bello',   'Male',   '1988-04-12', '+234 802 000 0001', 'tunde.bello@mail.test',  '12 Marina Road, Lagos',  'O+'),
  ('Chioma', 'Nwosu',   'Female', '1995-09-30', '+234 802 000 0002', 'chioma.nwosu@mail.test', '4 Allen Avenue, Ikeja',  'A+'),
  ('Ibrahim','Sule',    'Male',   '2016-01-21', '+234 802 000 0003', 'ibrahim.sule@mail.test', '7 Broad Street, Lagos',  'B+')
ON CONFLICT DO NOTHING;
