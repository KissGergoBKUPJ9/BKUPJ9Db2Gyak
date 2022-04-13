CREATE OR REPLACE PROCEDURE vevo_beszur(v_num NUMBER, v_nev VARCHAR, v_mail VARCHAR) IS
BEGIN
INSERT INTO vevo (ugyfelszam, nev, e_mail) VALUES (v_num, v_nev, v_mail);
 END vevo_beszur;

 
 BEGIN
 vevo_beszur(44556677, 'Valaki', '');
 END;
