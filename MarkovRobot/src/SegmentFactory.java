public class SegmentFactory {

	public static ISegment createSegment(String[] data) throws Exception {
		String segmentType = data[0];
		if (segmentType.contentEquals("LineSegment")) {
			return (ISegment) new LineSegment(data);
		} else {
			throw(new Exception("Unknown segment type: " + data[0]));
		}
	}

}
