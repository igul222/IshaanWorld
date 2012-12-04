package ishaanworld;

class ES {
    private static long benchmark_start_time;
    
    static void startBenchmark() {
        if(benchmark_start_time != -1)
            error("You can only run one ES benchmark at once!");
        benchmark_start_time = System.nanoTime();
    }
    
    static void endBenchmark() {
        double elapsed = (System.nanoTime()-benchmark_start_time)/1000000.0;
        benchmark_start_time = -1;
        System.out.println("ES benchmark: "+elapsed+"ms");
    }

    static void error(String message) {
        System.out.println("ERROR: "+message);
        System.out.println("Exiting...");
        System.exit(1); // indicates an abnormal exit
    }
}
