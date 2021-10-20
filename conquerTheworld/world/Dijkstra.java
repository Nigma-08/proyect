package world;

import java.util.*;


/**
 * Write a description of class Dijkstra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dijkstra
{
    /**
     * Calcula el camino con menor costo
     * @param graph el mundo 
     * @param source origen de la busqueda
     * @param destination destino de la busqueda
     */
    public static Nation calculateShortestPathFromSource(Graph graph, Nation source, Nation destination) {
        source.setDistance(0);
        
        Set<Nation> settledNodes = new HashSet<>();
        Set<Nation> unsettledNodes = new HashSet<>();
    
        unsettledNodes.add(source);
     
        while (unsettledNodes.size() != 0) {
            Nation currentNation = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNation);
            for(Route n:currentNation.getRoutes()){
                Nation nationAdj = n.getDestination();
                Integer pesoRuta = n.getValue();
                if(!settledNodes.contains(nationAdj)){
                    calculateMinimumDistance(nationAdj, pesoRuta, currentNation);
                    unsettledNodes.add(nationAdj);
                }
            }
            settledNodes.add(currentNation);
        }
        
        return destination;
    }
    
    /**
     * Obtiene la nacion con la cual tiene el camino de menor costo
     * @param unsettledNodes Naciones aun no visitadas.
     */
    private static Nation getLowestDistanceNode(Set < Nation > unsettledNodes) {
        Nation lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Nation node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    
    /**
     * Calcula la distancia minima de la nacion de origen a la de destino
     * @param evaluationNode destino nation
     * @param sourceNode origen nation
     * @param edgeWeigh peso del camino
     */
    private static void calculateMinimumDistance(Nation evaluationNode,
      Integer edgeWeigh, Nation sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Nation> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
