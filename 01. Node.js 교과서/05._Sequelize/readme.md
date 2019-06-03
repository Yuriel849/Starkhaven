## Queries with Sequelize
### First get the User model from models...
> const { User } = require('../models');
### INSERT INTO TABLE (name, age, married, comment) VALUES ('zwei', 10, 0, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')
> User.create({ <br>
    name: 'zwei', <br>
    age: 10, <br>
    married: false, <br>
    comment: 'ABCDEFGHIJKLMNOPQRSTUVWXYZ' <br>
});
### SELECT ALL
> User.findAll({});
### SELECT ONE ROW
> User.find({});
### SELECT A, B FROM TABLE ORDER BY C DESC
> User.findAll({ <br>
    attributes: ['A', 'B'], <br>
    order: [[ 'C', 'DESC' ]], => NOTE: array within array; outer array will contain all the columns to order by <br>
});
### SELECT A, B FROM TABLE WHERE A = C AND B < D
> const { User, Sequelize: { Op } } = require('../models'); <br>
User.findAll({ <br>
    attributes: ['A', 'B'], <br>
    where: { <br>
        A: C, <br>
        B: { [Op.lt]: D }, => Uses Op object within Sequelize object for operators <br>
    }, <br>
});
### SELECT A, B FROM TABLE WHERE C = D OR E > F
> const { User, Sequelize: { Op } } = require('../models'); <br>
User.findAll({ <br>
    attributes: ['A', 'B'], <br>
    where: { <br>
        [Op.or]: [{ C: D }, { E: { [Op.gt]: F } }], <br>
    }, <br>
});
>> Commonly used operators are... <br>
Op.gt = greater than <br>
Op.gte = greater than or equal <br>
Op.lt = lesser than <br>
Op.lte = lesser than or equal <br>
Op.ne = not equal <br>
Op.or = or <br>
Op.in = in (one of the elements in an array) <br>
Op.notIn = not in (not an element in an array) <br>
### SELECT A, B FROM TABLE LIMIT 1 OFFSET 1
> User.findAll({
    attributes: ['A', 'B'],
    limit: 1,
    offset: 1,
});
