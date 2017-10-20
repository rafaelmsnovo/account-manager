INSERT INTO PERSON (
    ID,
    CNPJ,
    COMPANY_NAME,
    FANTASY_NAME,
    CPF,
    FULL_NAME,
    BIRTH_DATE
  ) VALUES (
    1,
    null,
    null,
    null,
    '09219961601',
    'RAFAEL MORENO DA SILVA NOVO',
    TO_TIMESTAMP('23/03/1990', 'DD/MM/YYYY')
  );

INSERT INTO ACCOUNT (
    ID,
    ACCOUNT_NAME,
    CREATE_DATE,
    STATUS,
    BALANCE,
    PERSON_ID,
    ACCOUNT_PARENT_ID
  ) VALUES (
    1,
    'conta do rafao',
    now(),
    'ACTIVE',
    0,
    1,
    null
  );

