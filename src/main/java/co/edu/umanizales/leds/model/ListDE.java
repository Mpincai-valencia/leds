package co.edu.umanizales.leds.model;

import co.edu.umanizales.leds.exception.ListDEException;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListDE {
    private NodeDE head;
    private int size;

    private List<Led>leds=new ArrayList<>();
    public NodeDE getHead() {
        return head;
    }

    public void add(Led led)
    {
        if(head!=null)
        {
            NodeDE temp=head;
            while(temp.getNext()!=null)
            {
                temp.getNext();
            }
            NodeDE newNodeDE= new NodeDE(led);
            temp.setNext(newNodeDE);
            newNodeDE.setPrevious(temp);
        }
        else
        {
            head=new NodeDE(led);
        }

    }
    public void addToStart(Led led)
    {
        if (head!=null)
        {

            NodeDE newNodeDE= new NodeDE(led);
            newNodeDE.setNext(head);
            head.setPrevious(newNodeDE);
            head=newNodeDE;
        }
        else
        {
            head= new NodeDE(led);
        }
    }
    public void addInPosition(Led led, int position)
    {
        NodeDE temp=head;
        if(head!=null)
        {
            if(position==1)
            {
                addToStart(led);
            }
            else
            {
                for(int i=0;i<position-1;i++)
                {
                    temp = temp.getNext();
                }
                NodeDE newNodeDE= new NodeDE(led);
                temp.setNext(newNodeDE);
                newNodeDE.setPrevious(temp);
            }
        }

    }

    public void delete(String identification, int position)//este también se puede crear con una lista copia
    {   NodeDE temp=head;
        if(head!=null)
        {
            if(head.getData().equals(identification))
            {
                head=temp.getNext();
            }
            else
            {
                int pos=1;
                while(temp != null)
                {
                    temp = temp.getNext();
                    pos++;
                    if(pos == position-1 )
                    {
                        break;
                    }

                }
                temp.setNext(temp.getNext().getNext());
                temp.getNext().getNext().setPrevious(temp);
            }
        }
        else
        {
            head=null;
        }
    }
    /*
    Hay datos?
    Si
     -Llamo a un ayudante y le digo que se pare en cabeza
     -Le digo que mietras haya algo, se pase
     -Parado en cada uno, modifico su estado, fecha de encendido y apagado
    No
    -No hay datos
     */
    public void restartLedList()
    {
        if(head!=null)
        {
            NodeDE temp=head;
            while(temp!=null)
            {
                temp=temp.getNext();
                temp.getData().setState(false);
                temp.getData().setDateOn(null);
                temp.getData().setDateOff(null);
            }
        }
    }

    public void sleep(int milliseconds) throws ListDEException
    {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new ListDEException("Error en la operación de sleep");
        }
    }
    /*
    Hay datos?
    Si
    -Le digo a un ayudante que se pare en cabeza
    -Le digo a ese ayudante que recorra la lista
    -Busco la mitad de la lista
    -Cambio el estado de la mitad de la lista
    -Lo prendo
    -Cambio la hora que se prendió
    -LLamo a sleep
    -Lo apago
    -Cambio la hora en la que se apago
    -Llamo a otro ayudante para que m recorra la otra mitad de la lista
    -Voy prendiendo cada extremo cada que se vaya apagando el anterior
    -Cuando llegue a los extremos los dejo encendidos
    No
    -No hay datos
     */
    public void turnOnFromTheMiddle() throws ListDEException {
        if(head!=null)
        {
            NodeDE temp1=head;
            NodeDE temp2=head;
            int middle=(size+1)/2;
            int count=1;
            while (count <= middle && temp1 != null && temp2 != null)
            {

                if (count == middle)
                {
                    temp1.getData().setState(true);
                    temp1.getData().setDateOn(LocalTime.now());
                    sleep(1000);
                    temp1.getData().setState(false);
                    temp1.getData().setDateOff(LocalTime.now());
                }
                temp1 = temp1.getNext();
                temp2 = temp2.getPrevious();
                count++;
            }

            while (temp1 != null && temp2 != null)
            {
                temp1.getData().setState(true);
                temp1.getData().setDateOn(LocalTime.now());
                sleep(1000);
                temp1.getData().setState(false);
                temp1.getData().setDateOff(LocalTime.now());

                temp2.getData().setState(true);
                temp2.getData().setDateOn(LocalTime.now());
                sleep(1000);
                temp2.getData().setState(false);
                temp2.getData().setDateOff(LocalTime.now());

                temp1 = temp1.getNext();
                temp2 = temp2.getPrevious();

            }
            if(temp1.getNext()==null)
            {
                temp1.getData().setState(true);
                temp1.getData().setDateOn(LocalTime.now());
            }
            if(temp2.getPrevious()==null)
            {
                temp2.getData().setState(true);
                temp2.getData().setDateOn(LocalTime.now());
            }
        }
    }





}
