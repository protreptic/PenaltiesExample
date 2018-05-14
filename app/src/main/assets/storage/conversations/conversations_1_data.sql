INSERT INTO 'CONVERSATIONS' ('ID', 'NAME')
    VALUES
        ('1', 'Group conversation1'),
        ('2', 'Group conversation2'),
        ('3', 'Group conversation3');

INSERT INTO 'CONVERSATIONS_MEMBERS' ('ID', 'CONVERSATION_ID', 'NAME')
    VALUES
        ('1', '1', 'Peter1'),
        ('2', '1', 'Peter2'),
        ('3', '1', 'Peter3'),

        ('1', '2', 'Peter1'),
        ('2', '2', 'Peter2'),
        ('3', '2', 'Peter3'),

        ('1', '3', 'Peter1'),
        ('2', '3', 'Peter2'),
        ('3', '3', 'Peter3');