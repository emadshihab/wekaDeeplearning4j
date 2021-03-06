package weka.dl4j.zoo;

import org.deeplearning4j.nn.graph.ComputationGraph;
import weka.core.OptionMetadata;
import weka.dl4j.PretrainedType;
import weka.dl4j.zoo.keras.ResNet;
import weka.dl4j.zoo.keras.VGG;

public class KerasVGG extends AbstractZooModel {

    private static final long serialVersionUID = 4745962510013669482L;

    private VGG.VARIATION variation = VGG.VARIATION.VGG16;

    public KerasVGG() {
        setVariation(VGG.VARIATION.VGG16);
        setPretrainedType(PretrainedType.IMAGENET);
        setNumFExtractOutputs(4096);
        setFeatureExtractionLayer("fc2");
        setOutputLayer("predictions");
    }

    @OptionMetadata(
            description = "The model variation to use.",
            displayName = "Model Variation",
            commandLineParamName = "variation",
            commandLineParamSynopsis = "-variation <String>"
    )
    public VGG.VARIATION getVariation() {
        return variation;
    }

    public void setVariation(VGG.VARIATION var) {
        variation = var;
    }

    @Override
    public ComputationGraph init(int numLabels, long seed, int[] shape, boolean filterMode) {
        VGG vgg = new VGG();
        vgg.setVariation(variation);

        return attemptToLoadWeights(vgg, null, seed, numLabels, filterMode);
    }

    @Override
    public int[][] getShape() {
        int[][] shape = new int[1][];
        shape[0] = VGG.inputShape;
        return shape;
    }
}
