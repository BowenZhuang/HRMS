select y.year, avg(w.TMIN) as tmin from year y left join weather w on y.year=w.Year left join state s on s.ID=w.stateCode group by y.year;
select y.year as year,m.month as month,m.quarter quarter from year y, month m;
select 
    d.year year, d.month month, avg(w.TMIN) tmin
from
    test.year_month d
        left join
    weather w ON d.year = w.Year and d.month = w.Month
group by d.year , d.month;
select d.year year,d.quarter quarter, avg(w.TMIN) tmin from test.year_month d left join weather w on d.year=w.Year and d.month=w.Month group by d.year,d.quarter;

select 
    y.year, avg(w.TMIN) as timin
from
    year y
        left join
    weather w ON y.year = w.year
        left join
    state s ON s.ID = w.stateCode and w.UserID = 2
group by y.year 