# itMeet
מטרת האפליקציה : פלטפורמה ללימוד שפה באמצעות צ'אט בין אנשים בעולם
משתמש נרשם לאפליקציה ומזין איזה השפה הוא מעוניין ללמוד ואיזה שפה הוא יכול ללמד.
לאחר שהמשתמש רשום דף הבית שלו מכיל 2  מסכים
מסך ראשון set a meet : המשתמש מכניס תאריך ושעה שבו הוא מעוניין לבצע את הפגישה ובצד ימין יש פרגמנט שמכיל את כל הבחירות של המשתמש
בפרגמנט התחתון המשתמש רואה את כל המשתמשים האחרים שמעוניינים לקבוע פגישה באותו הזמן ומעוניינים ללמוד את השפה שהמשתמש הראשי יכול ללמד

פירוט על ה DB : כרגע האפליקציה משתמשת בSQLLITE  יש 2 טבלאות
טבלה 1 מכילה את המשתמשים שנרשמו לאפליקציה עם כל הנתונים האישיים שלהם
טבלה 2 מכילה את כל התאריכיםוהשעות של כל המשתמשים


מה הכוונות להמשך:
1.  לשנות את כל מסד הנתונים
  א. ליצור משתמש ב firebase
  ב. לממשק את הרישום של משתמש לפיירבייס
  ג. ליצור מחלקה שמאפשרת שליפת נתונים מהטבלה בשרת
  ד. ליצור אוביייקט חדש במקום טבלה מס' 2 כדי שנוכל לעשות את האלגוריתם שעושה התאמות בין משתמשים
  
2. לבנות צא'ט

3. UX/UI
  
  MVP:
  1. יכולת לבצע צא'ט בין משתמשים
  2. אפשרות כניסה לצא'ט רק בתאריך ובשעה שנקבע מראש
  3. לאפשר רק 20 דקות שיחה (באפלקציית הפרו יהיה אפשרות ליותר זמן)
  4. להוסיף לכל פגישה נושא שהמשתמש מעוניין לדבר עליו
  5. להוסיף לצא'ט אפשרות לקבל רשימת מילים בהתאם לנושא שהמשתמש בחר
  
  
  