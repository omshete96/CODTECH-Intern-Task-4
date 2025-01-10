import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ItemBasedRecommendation {
    public static void main(String[] args) throws IOException, TasteException {
        // Load dataset
        File file = new File("data.csv");
        DataModel model = new FileDataModel(file);

        // Compute item similarity
        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);

        // Create recommender
        Recommender recommender = new GenericItemBasedRecommender(model, similarity);

        // Recommend items for user ID 1
        List<RecommendedItem> recommendations = recommender.recommend(1, 3);
        System.out.println("Recommendations for User 1:");
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Item ID: " + recommendation.getItemID() + ", Value: " + recommendation.getValue());
        }
    }
}