import java.lang.reflect.*;
import java.util.*;


public class AnnotationValidator {


    public static String[] validate(Object obj){


        ArrayList<String> errors =
        new ArrayList<>();


        Class<?> c = obj.getClass();



        while(c != null){


            Field fields[] = c.getDeclaredFields();



            for(Field field:fields){


                try{


                    field.setAccessible(true);


                    Object value = field.get(obj);



                    if(field.isAnnotationPresent(Positive.class)){


                        if(value instanceof Number &&
                           ((Number)value).longValue() <= 0){


                            Positive p =
                            field.getAnnotation(Positive.class);


                            errors.add(
                            field.getName()+" "+p.message()
                            );

                        }

                    }



                    if(field.isAnnotationPresent(MaxLength.class)){


                        MaxLength m =
                        field.getAnnotation(MaxLength.class);



                        if(value != null &&
                           value.toString().length() > m.value()){


                            errors.add(
                            field.getName()
                            +" length must be <= "
                            +m.value()
                            );

                        }

                    }


                }
                catch(Exception e){

                    errors.add(e.getMessage());

                }

            }


            c=c.getSuperclass();

        }



        return errors.toArray(new String[0]);

    }

}