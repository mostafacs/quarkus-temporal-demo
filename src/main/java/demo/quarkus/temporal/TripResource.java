package demo.quarkus.temporal;

import demo.quarkus.temporal.saga.TripBookingWorkflow;
import io.quarkus.temporal.runtime.builder.WorkflowBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/trip")
@ApplicationScoped
public class TripResource {

    @Inject
    WorkflowBuilder workflowBuilder;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String start() {
        var uuid = UUID.randomUUID().toString();
        var tripBookingWorkflow = workflowBuilder.build(TripBookingWorkflow.class, uuid);
        tripBookingWorkflow.bookTrip(uuid);
        return "Trip booking workflow started successfully.";
    }
}