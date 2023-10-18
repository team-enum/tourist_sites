-- 오라클 DB
-- 계정 생성
CREATE USER springproject IDENTIFIED BY 1234;
-- 리소스 접근 권한을 주기
GRANT CONNECT, resource, dba TO springproject;
-- 계정 삭제
DROP USER springproject CASCADE;