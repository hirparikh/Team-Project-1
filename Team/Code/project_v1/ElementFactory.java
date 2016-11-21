
public class ElementFactory  
{
    
   // Object[] arr = new Object[5]; 
    
    public static IElement getElement(ElementType elementType,Object[] arr)
    {
     if (elementType == null) {
            return null;
        }
        IElement element;
        
       switch(elementType){
           case NODE: 
            element = getNode(arr);
            break;
          case EDGE:
            element = getEdge(arr);
            break;
         default:
            element = null;
            break;
        }
        return element;
    }
    
    private static IElement getNode(Object[] arr){
         return new Node((GameScreen)arr[0],(CardPart)arr[1]);   //first element is gameScreen and second element is cardPart object
    }
    
    private static IElement getEdge(Object[] arr){
        Node n1=(Node)arr[0];
        Node n2=(Node)arr[1];
        int weight=((Integer)arr[2]).intValue();
        String[] alignArr = (String[])arr[3];
        GameScreen gs= (GameScreen)arr[4];
        
        Edge e = new Edge(n1, n2, weight, alignArr, gs);
        n1.getEdgeSet().add(e);
        n2.getEdgeSet().add(e);
        e.attach(n1);
        e.attach(n2);
        n1.attach(e);
        n2.attach(e);
        
        return e;
    }
}
