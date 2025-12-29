-- Seed App Users
INSERT INTO app_user (id, username, password, role)
VALUES
('USR-001', 'vitou.u', '{noop}2025', 'ADMIN'),
('USR-002', 'bobby.s', '{noop}2025', 'USER'),
('USR-003', 'sarah.m', '{noop}2025', 'USER');

-- Seed Customers
INSERT INTO customer (id, full_name, phone_number)
VALUES
('CUS-001', 'John Smith', '+1-555-0101'),
('CUS-002', 'Emily Johnson', '+1-555-0102'),
('CUS-003', 'Michael Brown', '+1-555-0103'),
('CUS-004', 'Sarah Davis', '+1-555-0104'),
('CUS-005', 'David Wilson', '+1-555-0105'),
('CUS-006', 'Jessica Martinez', '+1-555-0106'),
('CUS-007', 'Daniel Anderson', '+1-555-0107'),
('CUS-008', 'Ashley Taylor', '+1-555-0108'),
('CUS-009', 'Christopher Thomas', '+1-555-0109'),
('CUS-010', 'Amanda White', '+1-555-0110');

-- Seed Employees
INSERT INTO employee (id, full_name, phone_number, specialization, active)
VALUES
('EMP-001', 'Sophia Rodriguez', '+1-555-0201', 'Hair Styling & Coloring', true),
('EMP-002', 'James Lee', '+1-555-0202', 'Barber & Men Grooming', true),
('EMP-003', 'Olivia Chen', '+1-555-0203', 'Nail Art & Manicure', true),
('EMP-004', 'William Park', '+1-555-0204', 'Massage Therapy', true),
('EMP-005', 'Emma Garcia', '+1-555-0205', 'Facial & Skincare', true);

-- Seed Employee Schedules (0=Sunday, 1=Monday, ..., 6=Saturday)
-- Sophia Rodriguez - Monday to Friday, 9 AM - 6 PM
INSERT INTO employee_schedule (id, employee_id, day_of_week, start_time, end_time, active)
VALUES
('SCH-001', 'EMP-001', 1, '09:00:00', '18:00:00', true),
('SCH-002', 'EMP-001', 2, '09:00:00', '18:00:00', true),
('SCH-003', 'EMP-001', 3, '09:00:00', '18:00:00', true),
('SCH-004', 'EMP-001', 4, '09:00:00', '18:00:00', true),
('SCH-005', 'EMP-001', 5, '09:00:00', '18:00:00', true),

-- James Lee - Tuesday to Saturday, 10 AM - 7 PM
('SCH-006', 'EMP-002', 2, '10:00:00', '19:00:00', true),
('SCH-007', 'EMP-002', 3, '10:00:00', '19:00:00', true),
('SCH-008', 'EMP-002', 4, '10:00:00', '19:00:00', true),
('SCH-009', 'EMP-002', 5, '10:00:00', '19:00:00', true),
('SCH-010', 'EMP-002', 6, '10:00:00', '19:00:00', true),

-- Olivia Chen - Monday to Saturday, 9 AM - 5 PM
('SCH-011', 'EMP-003', 1, '09:00:00', '17:00:00', true),
('SCH-012', 'EMP-003', 2, '09:00:00', '17:00:00', true),
('SCH-013', 'EMP-003', 3, '09:00:00', '17:00:00', true),
('SCH-014', 'EMP-003', 4, '09:00:00', '17:00:00', true),
('SCH-015', 'EMP-003', 5, '09:00:00', '17:00:00', true),
('SCH-016', 'EMP-003', 6, '09:00:00', '17:00:00', true),

-- William Park - Monday to Friday, 11 AM - 8 PM
('SCH-017', 'EMP-004', 1, '11:00:00', '20:00:00', true),
('SCH-018', 'EMP-004', 2, '11:00:00', '20:00:00', true),
('SCH-019', 'EMP-004', 3, '11:00:00', '20:00:00', true),
('SCH-020', 'EMP-004', 4, '11:00:00', '20:00:00', true),
('SCH-021', 'EMP-004', 5, '11:00:00', '20:00:00', true),

-- Emma Garcia - Wednesday to Sunday, 10 AM - 6 PM
('SCH-022', 'EMP-005', 3, '10:00:00', '18:00:00', true),
('SCH-023', 'EMP-005', 4, '10:00:00', '18:00:00', true),
('SCH-024', 'EMP-005', 5, '10:00:00', '18:00:00', true),
('SCH-025', 'EMP-005', 6, '10:00:00', '18:00:00', true),
('SCH-026', 'EMP-005', 0, '10:00:00', '18:00:00', true);

-- Seed Services
INSERT INTO service (id, name, duration_minutes, price, description, active)
VALUES
('SER-001', 'Haircut & Style', 45, 50.00, 'Professional haircut with styling', true),
('SER-002', 'Hair Coloring', 120, 120.00, 'Full hair coloring service', true),
('SER-003', 'Balayage', 180, 200.00, 'Premium balayage technique', true),
('SER-004', 'Mens Haircut', 30, 35.00, 'Classic mens haircut and grooming', true),
('SER-005', 'Beard Trim & Style', 20, 25.00, 'Professional beard trimming and styling', true),
('SER-006', 'Manicure', 45, 40.00, 'Classic manicure service', true),
('SER-007', 'Pedicure', 60, 55.00, 'Relaxing pedicure treatment', true),
('SER-008', 'Gel Nails', 75, 65.00, 'Long-lasting gel nail application', true),
('SER-009', 'Swedish Massage', 60, 80.00, 'Relaxing full body massage', true),
('SER-010', 'Deep Tissue Massage', 75, 95.00, 'Therapeutic deep tissue massage', true),
('SER-011', 'Facial Treatment', 60, 75.00, 'Customized facial treatment', true),
('SER-012', 'Anti-Aging Facial', 90, 110.00, 'Premium anti-aging facial treatment', true);

-- Seed Appointments (mix of different statuses and dates)
INSERT INTO appointment (id, customer_id, employee_id, service_id, start_time, end_time, status, notes, confirmed, cancelled_at, cancellation_reason)
VALUES
-- Confirmed upcoming appointments
('APT-001', 'CUS-001', 'EMP-001', 'SER-001', '2025-12-30 10:00:00+00', '2025-12-30 10:45:00+00', 'CONFIRMED', 'Regular customer, prefers shorter style', true, NULL, NULL),
('APT-002', 'CUS-002', 'EMP-003', 'SER-006', '2025-12-30 11:00:00+00', '2025-12-30 11:45:00+00', 'CONFIRMED', 'Allergic to certain nail polish brands', true, NULL, NULL),
('APT-003', 'CUS-003', 'EMP-004', 'SER-009', '2025-12-30 14:00:00+00', '2025-12-30 15:00:00+00', 'CONFIRMED', NULL, true, NULL, NULL),
('APT-004', 'CUS-004', 'EMP-005', 'SER-011', '2025-12-30 15:30:00+00', '2025-12-30 16:30:00+00', 'CONFIRMED', 'Sensitive skin', true, NULL, NULL),

-- Booked but not confirmed
('APT-005', 'CUS-005', 'EMP-002', 'SER-004', '2025-12-31 09:00:00+00', '2025-12-31 09:30:00+00', 'BOOKED', NULL, false, NULL, NULL),
('APT-006', 'CUS-006', 'EMP-001', 'SER-002', '2025-12-31 10:00:00+00', '2025-12-31 12:00:00+00', 'BOOKED', 'First time hair coloring', false, NULL, NULL),
('APT-007', 'CUS-007', 'EMP-003', 'SER-008', '2025-12-31 13:00:00+00', '2025-12-31 14:15:00+00', 'BOOKED', NULL, false, NULL, NULL),

-- Completed appointments (past dates)
('APT-008', 'CUS-001', 'EMP-001', 'SER-001', '2025-12-20 10:00:00+00', '2025-12-20 10:45:00+00', 'COMPLETED', NULL, true, NULL, NULL),
('APT-009', 'CUS-002', 'EMP-005', 'SER-011', '2025-12-22 14:00:00+00', '2025-12-22 15:00:00+00', 'COMPLETED', NULL, true, NULL, NULL),
('APT-010', 'CUS-008', 'EMP-004', 'SER-010', '2025-12-23 11:00:00+00', '2025-12-23 12:15:00+00', 'COMPLETED', 'Focus on lower back', true, NULL, NULL),
('APT-011', 'CUS-009', 'EMP-002', 'SER-004', '2025-12-24 09:30:00+00', '2025-12-24 10:00:00+00', 'COMPLETED', NULL, true, NULL, NULL),

-- Cancelled appointments
('APT-012', 'CUS-003', 'EMP-001', 'SER-003', '2025-12-28 13:00:00+00', '2025-12-28 16:00:00+00', 'CANCELLED', NULL, true, '2025-12-27 10:30:00+00', 'Customer had to reschedule due to personal emergency'),
('APT-013', 'CUS-010', 'EMP-003', 'SER-007', '2025-12-29 10:00:00+00', '2025-12-29 11:00:00+00', 'CANCELLED', NULL, false, '2025-12-28 15:00:00+00', 'Customer found another salon closer to home'),

-- No show appointment
('APT-014', 'CUS-004', 'EMP-002', 'SER-005', '2025-12-25 14:00:00+00', '2025-12-25 14:20:00+00', 'NO_SHOW', NULL, true, NULL, NULL),

-- Future appointments for next week
('APT-015', 'CUS-005', 'EMP-001', 'SER-002', '2026-01-02 10:00:00+00', '2026-01-02 12:00:00+00', 'CONFIRMED', 'New color - dark brown to blonde', true, NULL, NULL),
('APT-016', 'CUS-006', 'EMP-004', 'SER-009', '2026-01-03 15:00:00+00', '2026-01-03 16:00:00+00', 'BOOKED', NULL, false, NULL, NULL),
('APT-017', 'CUS-007', 'EMP-005', 'SER-012', '2026-01-04 11:00:00+00', '2026-01-04 12:30:00+00', 'CONFIRMED', 'Birthday treat', true, NULL, NULL),
('APT-018', 'CUS-008', 'EMP-003', 'SER-006', '2026-01-05 09:00:00+00', '2026-01-05 09:45:00+00', 'BOOKED', NULL, false, NULL, NULL),
('APT-019', 'CUS-009', 'EMP-002', 'SER-004', '2026-01-06 16:00:00+00', '2026-01-06 16:30:00+00', 'CONFIRMED', NULL, true, NULL, NULL),
('APT-020', 'CUS-010', 'EMP-001', 'SER-001', '2026-01-07 13:00:00+00', '2026-01-07 13:45:00+00', 'BOOKED', 'Referred by friend', false, NULL, NULL);