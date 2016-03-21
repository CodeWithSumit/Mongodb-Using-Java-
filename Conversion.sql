Select T.string_id from lang t
where T.String_ID not in
(Select A.String_Id from
type4_string_trans t4ts
inner join  
 (Select distinct  t4s.String_ID,l.[English Txt],l.Description,l.Language_Code
from lang L inner join
type4_string t4S on l.String_ID = t4s.String_Id) A
on t4ts.Eng_Description = A.[English Txt])
