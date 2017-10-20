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
    'conta do rafa 1',
    now(),
    'ACTIVE',
    -120.50,
    1,
    null
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
      2,
      'conta do rafa 2',
      now(),
      'ACTIVE',
      120.50,
      1,
      1
    );


  INSERT INTO `TRANSACTION` (
      ID,
      CREATE_DATE,
      `VALUE`,
      TYPE,
      REVERSED,
      ACCOUNT_ID_IN,
      ACCOUNT_ID_OUT
    ) VALUES (
      1,
      now(),
      120.50,
      'TRANSFER',
      0,
      2,
      1
    );


