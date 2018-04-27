package com.sun.xiaotian.demo.storm.word;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;
import java.util.Random;

public class RandomSentenceSpout extends BaseRichSpout {

    private SpoutOutputCollector spoutOutputCollector;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.spoutOutputCollector = collector;
    }

    @Override
    public void nextTuple() {
        Utils.sleep(1000);
        spoutOutputCollector.emit(new Values(sentences[random.nextInt(sentences.length)]));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("RandomSentenceSpout"));
    }


    private static String sentence = "Ice storm — Ice storms are one of the most dangerous forms of winter storms. When surface temperatures are below freezing, but a thick layer of above-freezing air remains aloft, rain can fall into the freezing layer and freeze upon impact into a glaze of ice. In general, 8 millimetres (0.31 in) of accumulation is all that is required, especially in combination with breezy conditions, to start downing power lines as well as tree limbs.[3] Ice storms also make unheated road surfaces too slick to drive upon. Ice storms can vary in time range from hours to days and can cripple small towns and large urban centers alike.\n" +
            "Blizzard — There are varying definitions for blizzards, both over time and by location. In general, a blizzard is accompanied by gale-force winds, heavy snow (accumulating at a rate of at least 5 centimeters (2 in) per hour), and very cold conditions (below approximately −10 degrees Celsius or 14 F). Lately, the temperature criterion has fallen out of the definition across the United States[4]\n" +
            "Snowstorm — A heavy fall of snow accumulating at a rate of more than 5 centimeters (2 in) per hour that lasts several hours. Snow storms, especially ones with a high liquid equivalent and breezy conditions, can down tree limbs, cut off power, and paralyze travel over a large region.\n" +
            "Coastal Storm — large wind waves and/or storm surge that strike the coastal zone. Their impacts include coastal erosion and coastal flooding[5]\n" +
            "Ocean Storm — Storm conditions out at sea are defined as having sustained winds of 48 knots (55 mph or 90 km/h) or greater.[6] Usually just referred to as a storm, these systems can sink vessels of all types and sizes.\n" +
            "Firestorm — Firestorms are conflagrations which attain such intensity that they create and sustain their own wind systems. It is most commonly a natural phenomenon, created during some of the largest bushfires, forest fires, and wildfires. The Peshtigo Fire is one example of a firestorm. Firestorms can also be deliberate effects of targeted explosives such as occurred as a result of the aerial bombings of Dresden. Nuclear detonations generate firestorms if high winds are not present.\n" +
            "Dust devil — a small, localized updraft of rising air.\n" +
            "Wind storm— A storm marked by high wind with little or no precipitation.[7] Windstorm damage often opens the door for massive amounts of water and debris to cause further damage to a structure.[8] European windstorms and derechos are two type of windstorms.[9] High wind is also the cause of sandstorms in dry climates.\n" +
            "Squall — sudden onset of wind increase of at least 16 knots (30 km/h) or greater sustained for at least one minute.\n" +
            "Gale — An extratropical storm with sustained winds between 34–48 knots (39–55 mph or 63–90 km/h).[6]\n" +
            "Thunderstorm — A thunderstorm is a type of storm that generates lightning and the attendant thunder. It is normally accompanied by heavy precipitation. Thunderstorms occur throughout the world, with the highest frequency in tropical rainforest regions where there are conditions of high humidity and temperature along with atmospheric instability. These storms occur when high levels of condensation form in a volume of unstable air that generates deep, rapid, upward motion in the atmosphere. The heat energy creates powerful rising air currents that swirl upwards to the tropopause. Cool descending air currents produce strong downdraughts below the storm. After the storm has spent its energy, the rising currents die away and downdraughts break up the cloud. Individual storm clouds can measure 2–10 km across.\n" +
            "Tropical cyclone — A tropical cyclone is a storm system with a closed circulation around a centre of low pressure, fueled by the heat released when moist air rises and condenses. The name underscores its origin in the tropics and their cyclonic nature. Tropical cyclones are distinguished from other cyclonic storms such as nor'easters and polar lows by the heat mechanism that fuels them, which makes them \"warm core\" storm systems.\n" +
            "Tropical cyclones form in the oceans if the conditions in the area are favorable, and depending on their strength and location, there are various terms by which they are called, such as tropical depression, tropical storm, hurricane and typhoon.[10]\n" +
            "Hailstorm — a type of storm that precipitates round chunks of ice. Hailstorms usually occur during regular thunder storms. While most of the hail that precipitates from the clouds is fairly small and virtually harmless, there are occasional occurrences of hail greater than 2 inches (5 cm) in diameter that can cause much damage and injuries.\n" +
            "\n" +
            "A tornado in Binger, Oklahoma during the 1981 outbreak.\n" +
            "Tornado — A tornado is a violent, destructive wind storm occurring on land. Usually its appearance is that of a dark, funnel-shaped cloud. Often tornadoes are preceded by thunderstorms and a wall cloud. They are often called the most destructive of storms, and while they form all over the world, the interior of the United States is the most prone area, especially throughout Tornado Alley.";

    private static String[]  sentences = sentence.split("\\n");

    private Random random = new Random();

}
